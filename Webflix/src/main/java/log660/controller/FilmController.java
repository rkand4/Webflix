/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */

package log660.controller;

import log660.dao.SystemDAO;
import log660.model.Client;
import log660.model.Copie;
import log660.model.Film;
import log660.model.Location;
import log660.utils.WebFlixValidationException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/film")
@Scope("request")
public class FilmController extends BaseController{

    private SystemDAO systemDAO = new SystemDAO();

    public static final String[] emptyArray = {};

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index(
            @RequestParam(value = "filmTitle", defaultValue = "") String filmTitle,
            @RequestParam(value = "yearStart", defaultValue = "") String yearStart,
            @RequestParam(value = "yearEnd", defaultValue = "") String yearEnd,
            @RequestParam(value = "country", defaultValue = "") String country,
            @RequestParam(value = "language", defaultValue = "") String language,
            @RequestParam(value = "genre", required = false) String[] genre,
            @RequestParam(value = "directorName", defaultValue = "") String directorName,
            @RequestParam(value = "actorName", defaultValue = "") String actorName
    ) {
        ModelAndView mv = new ModelAndView("film/index");

        mv.addObject("genres",systemDAO.getGenresComboBox());
        mv.addObject("pays",systemDAO.getPaysComboBox());
        mv.addObject("langues",systemDAO.getLanguesComboBox());

        if(!allEquals("",actorName,country,directorName,filmTitle,language,yearEnd,yearStart) || (genre != null && genre.length > 0)){
            List<Film> list = systemDAO.search(actorName, country, directorName, filmTitle, language, yearEnd, yearStart, genre);
            mv.addObject("films", list);
        }

        return mv;
    }


    @RequestMapping(value = "/{idFilm}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable("idFilm")Integer idFilm){
        ModelAndView modelAndView = new ModelAndView("film/view");

        Film film = systemDAO.getFilm(idFilm);

        if(film != null){
            List<Copie> availableCopies = systemDAO.getAvailableCopies(idFilm);

            if(userSession.getClient() != null) {
                List<Location> locations = systemDAO.getNonReturnedLocations(userSession.getClient().getIdUtilisateur());

                modelAndView.addObject("nbLocationsClient", locations.size());
            }

            modelAndView.addObject("film", film);
            modelAndView.addObject("nbCopiesDisponible", availableCopies.size());

        }

        return modelAndView;
    }

    @RequestMapping(value = "/{idFilm}/rent", method = RequestMethod.POST)
    public ModelAndView rent(@PathVariable("idFilm")Integer idFilm, RedirectAttributes redirect){

        if(userSession.getClient() == null){
            redirect.addFlashAttribute("loginRequired", "You must be logged in to rent a copy.");
            redirect.addAttribute("redirecturl", "/film/" + idFilm);
            return new ModelAndView("redirect:/auth/login");
        }

        Client client = userSession.getClient();

        try {
            Copie c = systemDAO.rentCopie(idFilm, client);
            redirect.addFlashAttribute("rentSuccess", "Succesfully rented copy " + c.getCodeCopie());
        }
        catch(WebFlixValidationException ex){
            redirect.addFlashAttribute("rentError", ex.getMessage());
        }

        return new ModelAndView("redirect:/film/" + idFilm);
    }

    private boolean allEquals(String value, String... params)
    {
        for (String param:params){
            if(!value.equals(param))
                return false;
        }
        return true;
    }
}

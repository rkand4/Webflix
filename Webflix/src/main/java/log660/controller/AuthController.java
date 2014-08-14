package log660.controller;

import log660.model.Client;
import log660.viewmodel.UserSession;
import log660.dao.SystemDAO;
import log660.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Alex on 2014-05-29.
 */

@Controller
@RequestMapping("/auth/")
@Scope("request")
public class AuthController extends BaseController{

    @Autowired
    private UserSession userSession;

    private SystemDAO systemDAO = new SystemDAO();

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView login() {
        return new ModelAndView("auth/login");
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ModelAndView logout() {
        return signout();
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public ModelAndView register() {
        return new ModelAndView("auth/register");
    }


    @RequestMapping(value = "signin", method = RequestMethod.POST)
    public ModelAndView signin(String email, String password,String redirecturl, RedirectAttributes redirect) {

        boolean areFieldsValid = true;

        if ( email == null || email.isEmpty() ){
            redirect.addFlashAttribute("emailFieldError", "Email cannot be empty");
            areFieldsValid = false;
        }
        if(password == null || password.isEmpty()){
            redirect.addFlashAttribute("passwordFieldError", "Password cannot be empty");
            areFieldsValid=false;
        }

        if(!areFieldsValid){
            return new ModelAndView("redirect:/auth/login");
        }


        Client user = systemDAO.getClientFromCredentials(email, password);

        if(user == null){
            redirect.addFlashAttribute("loginError", "Invalid Username or Password");
            return new ModelAndView("redirect:/auth/login");
        }
        else{
            userSession.setClient(user);

            if(redirecturl == null || redirecturl.isEmpty())
                return new ModelAndView("redirect:/");
            else
                return new ModelAndView("redirect:" + redirecturl);
        }
    }

    @RequestMapping(value = "signout", method = RequestMethod.POST)
    public ModelAndView signout() {
        userSession.setClient(null);
        return new ModelAndView("redirect:/");
    }


}

package log660.controller;

import log660.model.Client;
import log660.viewmodel.UserSession;
import log660.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by Alex on 2014-06-02.
 */
@Controller
@Scope("request")
public class BaseController {

    @Autowired
    protected UserSession userSession;

    @ModelAttribute("utilisateur")
    public Client getClient() {
        return userSession.getClient();
    }
}

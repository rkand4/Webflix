package log660.viewmodel;

import log660.model.Client;
import log660.model.Utilisateur;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Alex on 2014-06-02.
 */
@Component
@Scope("session")
public class UserSession {

    private Client client = null;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}

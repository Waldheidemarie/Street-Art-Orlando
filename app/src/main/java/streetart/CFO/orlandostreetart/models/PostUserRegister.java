package streetart.CFO.orlandostreetart.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Eric on 3/21/2019.
 */
public class PostUserRegister {

    private String nickname;

    private String email;

    private String password;

    public PostUserRegister(String nickname, String email, String password) {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return nickname;
    }

    public void setName(String name) {
        this.nickname = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package in.haeg.list;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;
import com.google.appengine.api.users.User;

@PersistenceCapable public class Note {
    @PrimaryKey @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) private Key key;
    @Persistent private User                                                          m_User;
    @Persistent private String                                                        m_Name;
    @Persistent private Text                                                          m_Content;

    public Note(User a_User, String a_Name, Text a_Content) {
        m_User = a_User;
        m_Name = a_Name;
        m_Content = a_Content;
    }

    public Note(User a_User, String a_Name, String a_Content) {
        this(a_User, a_Name, new Text(a_Content));
    }

    public User getUser() {
        return m_User;
    }

    public void setUser(User a_user) {
        m_User = a_user;
    }

    public String getName() {
        return m_Name;
    }

    public void setName(String a_name) {
        m_Name = a_name;
    }

    public Text getDescription() {
        return m_Content;
    }

    public void setDescription(String a_description) {
        m_Content = new Text(a_description);
    }

    public Key getKey() {
        return key;
    }
}

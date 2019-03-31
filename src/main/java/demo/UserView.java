package demo;

import java.util.Date;

public class UserView {
    private final String _userName;
    private final Date _date;
    private final String _namespace;

    public UserView(final String userName, final Date date, final String namespace) {
        _userName = userName;
        _date = date;
        _namespace = namespace;
    }

    public String getUserName() {
        return _userName;
    }

    public Date getDate() {
        return _date;
    }

    public String getNamespace() {
        return _namespace;
    }
}

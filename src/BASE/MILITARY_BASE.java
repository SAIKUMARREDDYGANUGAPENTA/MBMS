package BASE;

abstract class base
{
    abstract String getBasename();
    abstract String getBaseID();
    abstract String getEmployeeID();
    abstract String getBase_password();
}

public class MILITARY_BASE extends base
{
    public String Basename;
    public String BaseID;
    public String EmployeeID;
    public String Base_password;

    public MILITARY_BASE(String basename, String baseID, String EmployeeID, String base_password)
    {
        Basename = basename;
        BaseID = baseID;
        this.EmployeeID = EmployeeID;
        Base_password = base_password;
    }

    public String getBasename()
    {
        return Basename;
    }

    public String getBaseID()
    {
        return BaseID;
    }

    public String getEmployeeID()
    {
        return EmployeeID;
    }

    public String getBase_password()
    {
        return Base_password;
    }

}
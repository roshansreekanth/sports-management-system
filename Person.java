import java.io.Serializable;

public class Person implements Serializable
{
    private Name name;
    private String phone;
    private String email;

    public Person(String name, String phone, String email) 
    {
      this.name = new Name(name);
      this.phone = phone;
      this.email = email;
    }

  //----------------
  //  Getters
  //----------------

    public Name getName()
    {
      return this.name;
    }

    public String getPhone()
    {
      return this.phone;
    }

    public String getEmail()
    {
      return this.email;
    }

  //----------------
  //  Setters
  //----------------

    public void setName(Name newName)
    {
      this.name = newName;
    }

    public void setPhone(String newNumber)
    {
      this.phone = newNumber;
    }

  public void setEmail(String newMail)
  {
    this.email = newMail;
  }

  //----------------
  //  Setters
  //----------------

  public boolean compare(Person personObj)
  {
    if(this.email.equals(personObj.getEmail()) || this.phone.equals(personObj.getPhone()))
    {
      return true;
    }

    else
    {
      return false;
    }
  }

  public String toString()
  {
    return "Name: " + this.name + " Phone: " + this.phone + " Email: " + this.email;
  }
}
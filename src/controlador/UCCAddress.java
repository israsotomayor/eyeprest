package controlador;

import dao.DAOAddress;
import dominio.Address;
import dominio.Person;
import java.util.List;

public class UCCAddress {

    public List<Address> getAddressPerPerson(Person person) {

        DAOAddress da = new DAOAddress();
        return da.getAddressPerCustomer(person);
    }
}

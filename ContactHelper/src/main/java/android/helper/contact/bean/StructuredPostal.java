package android.helper.contact.bean;

import com.witness.utils.db.annotation.DBColumn;
import com.witness.utils.db.annotation.DBPrimaryKey;
import com.witness.utils.db.annotation.DBTableName;

/**
 * Created by danger on 15/3/29.
 */
@DBTableName(name = Contact.Columns.TABLE_NAME_CONTACT_DATA)
public class StructuredPostal implements IContactData{

    @DBColumn(name = Contact.Columns.DATA_TYPE)
    private static final int dtype = Type.TYPE_STRUCTURED_POSTAL;


    @DBPrimaryKey(name = Contact.Columns._ID, autoIncrement = true)
    long id = 0;

    @DBColumn(name = Contact.Columns.CONTACT_ID)
    long cid;


    @DBColumn(name = Contact.Columns.DATA1)
    String formatted_address;

    @DBColumn(name = Contact.Columns.DATA2)
    String type;

    @DBColumn(name = Contact.Columns.DATA3)
    String label;

    @DBColumn(name = Contact.Columns.DATA4)
    String street;

    @DBColumn(name = Contact.Columns.DATA5)
    String pobox;

    @DBColumn(name = Contact.Columns.DATA6)
    String neighborhood;

    @DBColumn(name = Contact.Columns.DATA7)
    String city;

    @DBColumn(name = Contact.Columns.DATA8)
    String region;

    @DBColumn(name = Contact.Columns.DATA9)
    String postcode;

    @DBColumn(name = Contact.Columns.DATA10)
    String country;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public long getCid() {
        return cid;
    }

    @Override
    public void setCid(long cid) {
        this.cid = cid;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPobox() {
        return pobox;
    }

    public void setPobox(String pobox) {
        this.pobox = pobox;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

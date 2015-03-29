package android.helper.contact.bean;

import com.witness.utils.db.annotation.DBColumn;
import com.witness.utils.db.annotation.DBPrimaryKey;
import com.witness.utils.db.annotation.DBTableName;

/**
 * Created by danger on 15/3/29.
 */
@DBTableName(name = Contact.Columns.TABLE_NAME_CONTACT_DATA)
public class SipAddress implements IContactData{
    @DBColumn(name = Contact.Columns.DATA_TYPE)
    private static final int dtype = Type.TYPE_SIP;



    @DBPrimaryKey(name = Contact.Columns._ID, autoIncrement = true)
    long id = 0;

    @DBColumn(name = Contact.Columns.CONTACT_ID)
    long cid;
    

    @DBColumn(name = Contact.Columns.DATA1)
    private String sip_address;

    @DBColumn(name = Contact.Columns.DATA2)
    private int type;

    @DBColumn(name = Contact.Columns.DATA3)
    private String label;

    @Override
    public long getCid() {
        return cid;
    }

    @Override
    public void setCid(long cid) {
        this.cid = cid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSip_address() {
        return sip_address;
    }

    public void setSip_address(String sip_address) {
        this.sip_address = sip_address;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}

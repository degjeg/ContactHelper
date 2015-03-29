package android.helper.contact;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.helper.contact.bean.Contact;
import android.helper.contact.bean.Data;
import android.helper.contact.bean.Email;
import android.helper.contact.bean.Event;
import android.helper.contact.bean.IContactData;
import android.helper.contact.bean.IM;
import android.helper.contact.bean.NickName;
import android.helper.contact.bean.Organization;
import android.helper.contact.bean.Phone;
import android.helper.contact.bean.Photo;
import android.helper.contact.bean.Relation;
import android.helper.contact.bean.SipAddress;
import android.helper.contact.bean.StructuredName;
import android.helper.contact.bean.StructuredPostal;
import android.helper.contact.bean.Type;
import android.helper.contact.bean.WebSite;
import android.util.Log;

import com.witness.utils.db.ESQLiteDatabase;
import com.witness.utils.db.util.TAEntityBuilder;

import java.util.List;

/**
 * Created by danger on 15/3/29.
 */
public class ContactDbHelper {
    private static final String TAG = "ContactHelper";
    ESQLiteDatabase db;
    private final static String DB_NAME = "contact";
    private final static int DB_VERSION = 1;

    public ContactDbHelper(Context context) {
        ESQLiteDatabase.TADBParams params = new ESQLiteDatabase.TADBParams(DB_NAME, DB_VERSION);
        db = new ESQLiteDatabase(context, params);

        db.openWritable(new ESQLiteDatabase.TADBUpdateListener() {
            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        });
    }


    public void createTables() {
        db.creatTable(Contact.class);
        db.creatTable(Data.class);
    }

    public long insert(Contact contact) {
        if (contact == null) {
            return -1;
        }
        long contactID = db.insert(contact);
        List<IContactData> datas = contact.getDatas();
        contact.setCId(contactID);

        for (IContactData data : datas) {
            data.setCid(contactID);
            db.insert(data);
        }

        return contactID;
    }

    public List<Contact> querySimple() {
        return db.query(Contact.class, false, null, null, null, null, null);

    }

    public Contact query(long cid, Contact contact) {

        if (contact == null && cid == 0) {
            return contact;
        } else if (contact == null) {
            contact = new Contact();
        }

        String tabName = Contact.Columns.TABLE_NAME_CONTACT_DATA;
        String where = "cid=?";
        String arg[] = {"" + contact.getCId()};
        Cursor cursor = db.getmSQLiteDatabase().query(tabName, null, where, arg, null, null, null);

        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        int type = cursor.getInt(cursor.getColumnIndex(Contact.Columns.DATA_TYPE));

                        IContactData data = createDataByType(type);

                        if (data != null) {
                            TAEntityBuilder.buildQueryOneEntity(data.getClass(), cursor);
                            contact.addData(data);
                        }

                    } while (cursor.moveToNext());
                } else {
                    Log.d(TAG, "cursor empty");
                }
            } finally {
                cursor.close();
            }

        } else {
            Log.d(TAG, "cursor null");
        }

        return contact;
    }

    public Contact update(Contact contact) {
        if (contact == null) {
            return contact;
        }

        String where = "cid=" + contact.getCId();
        db.delete(Contact.Columns.TABLE_NAME_CONTACT_DATA, where, null);
        db.delete(Contact.Columns.TABLE_NAME_CONTACT, where, null);
        insert(contact);

        return contact;
    }

    public boolean update(IContactData data) {
        return db.update(data, Contact.Columns.CONTACT_ID + "=" + data.getCid());
    }


    private IContactData createDataByType(int type) {
        IContactData data = null;
        switch (type) {
            case Type.TYPE_EMAIL:// = 1;
                data = new Email();
                break;
            case Type.TYPE_EVENT:// = 2;
                data = new Event();
                break;
            case Type.TYPE_IM://= 3;
                data = new IM();
                break;
            case Type.TYPE_NICKNAME:// = 4;
                data = new NickName();
                break;
            case Type.TYPE_ORGANIZATION:// = 5;
                data = new Organization();
                break;
            case Type.TYPE_PHONE://= 6;
                data = new Phone();
                break;
            case Type.TYPE_PHOTO://= 7;
                data = new Photo();
                break;
            case Type.TYPE_RELATION://= 8;
                data = new Relation();
                break;
            case Type.TYPE_SIP://= 9;
                data = new SipAddress();
                break;
            case Type.TYPE_STRUCTURED_NAME://= 10;
                data = new StructuredName();
                break;
            case Type.TYPE_STRUCTURED_POSTAL:// = 11;
                data = new StructuredPostal();
                break;
            case Type.TYPE_WEBSITE://= 12;
                data = new WebSite();
                break;
        }
        return data;
    }
}

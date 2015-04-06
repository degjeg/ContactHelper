package android.helper.contact;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.helper.contact.bean.Contact;
import android.helper.contact.bean.Email;
import android.helper.contact.bean.Event;
import android.helper.contact.bean.Group;
import android.helper.contact.bean.IM;
import android.helper.contact.bean.NickName;
import android.helper.contact.bean.Phone;
import android.helper.contact.bean.Relation;
import android.helper.contact.bean.SipAddress;
import android.helper.contact.bean.StructuredName;
import android.helper.contact.bean.StructuredPostal;
import android.helper.contact.bean.WebSite;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Auther:Danger
 * Android 通讯录的辅助工具类，实现增删改查等功能
 */
public class ContactHelper {

    private static final String TAG = "ContactHelper";
    Context context;
    ContentResolver cr;

    private interface IContactListener {
        int ERROR_UNKNOWN = 1;

        int onCount(int count);

        void onContact(Contact contact);

        void onError(int reason, Exception exInfo);
    }


    private interface IQueryDataListener {
        void onData(Cursor c);

    }

    public ContactHelper(Context context) {
        this.context = context;
        this.cr = context.getContentResolver();

    }


    public int getCount() {
        Cursor cursor = null;
        int countCount = 0;
        try {
            String projection[] = {
                    ContactsContract.Contacts._ID,
            };
            cr.query(ContactsContract.Contacts.CONTENT_URI, projection, null, null, null);

            if (cursor != null) {
                countCount = cursor.getCount();
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return countCount;
    }


    public List<Contact> querySimple() {
        Cursor cursor = null;
        List<Contact> list = new ArrayList<Contact>();
        try {

            String projection[] = {
                    ContactsContract.Contacts._ID,

                    ContactsContract.Contacts.DISPLAY_NAME,
                    ContactsContract.Contacts.STARRED,
            };

            String order = ContactsContract.Contacts._ID;
            cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, projection, null, null, order);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        Contact contact = new Contact();
                        contact.setStatus(0);
                        list.add(contact);

                        long cid = cursor.getLong(cursor.getColumnIndex(android.provider.ContactsContract.Contacts._ID));
                        //获得联系人姓名
                        String displayName = cursor.getString(cursor.getColumnIndex(android.provider.ContactsContract.Contacts.DISPLAY_NAME));

                        contact.setCId(cid);
                        contact.setDisplayName(displayName);

                    } while (cursor.moveToNext());
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return list;
    }


    public void query(List<Contact> contacts) {

        if (contacts == null || contacts.size() == 0) {
            return;
        }
        HashMap<Long, Contact> tmpMap = new HashMap<Long, Contact>();
        for (Contact contact : contacts) {
            tmpMap.put(contact.getCId(), contact);
        }
        String projection[] = {
                ContactsContract.Data._ID,
                ContactsContract.Data.RAW_CONTACT_ID,
                ContactsContract.Data.MIMETYPE,
                ContactsContract.Data.DATA1,
                ContactsContract.Data.DATA2,
                ContactsContract.Data.DATA3,
                ContactsContract.Data.DATA4,
                ContactsContract.Data.DATA5,
                ContactsContract.Data.DATA6,
                ContactsContract.Data.DATA7,
                ContactsContract.Data.DATA8,
                ContactsContract.Data.DATA9,
                ContactsContract.Data.DATA10,
                ContactsContract.Data.DATA11,
                ContactsContract.Data.DATA12,
                ContactsContract.Data.DATA13,
                ContactsContract.Data.DATA14

        };

        String selection = null;//ContactsContract.Data.DELETED + "><?";
        String selectionArgs[] = null;//{"0"};
        String order = "";
        Cursor cursor = null;

        try {
            cursor = cr.query(ContactsContract.Data.CONTENT_URI, projection, selection, selectionArgs, order);

            if (cursor == null || !cursor.moveToFirst()) {
                return;
            }

            do {
                String mineType = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.MIMETYPE));
                long rawContactID = cursor.getLong(cursor.getColumnIndex(ContactsContract.Data.RAW_CONTACT_ID));

                Contact contact = tmpMap.get(rawContactID);
                if (contact == null) {
                    continue;
                }
                if (TextUtils.equals(mineType, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)) {
                    Phone phone = new Phone();
                    phone.setId(cursor.getLong(cursor.getColumnIndex(ContactsContract.Data._ID)));
                    phone.setLabel(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.LABEL)));
                    phone.setNumber(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                    phone.setType(cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE)));
                    contact.addData(phone);
                } else if (TextUtils.equals(mineType, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)) {
                    Email email = new Email();
                    email.setId(cursor.getLong(cursor.getColumnIndex(ContactsContract.Data._ID)));
                    email.setLabel(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.LABEL)));
                    email.setAddress(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA1)));
                    email.setType(cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE)));
                    contact.addData(email);
                } else if (TextUtils.equals(mineType, ContactsContract.CommonDataKinds.Event.CONTENT_ITEM_TYPE)) {
                    Event event = new Event();
                    event.setId(cursor.getLong(cursor.getColumnIndex(ContactsContract.Data._ID)));
                    event.setLabel(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Event.LABEL)));
                    event.setStartDate(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Event.START_DATE)));
                    event.setType(cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Event.TYPE)));
                    contact.addData(event);
                } else if (TextUtils.equals(mineType, ContactsContract.CommonDataKinds.Nickname.CONTENT_ITEM_TYPE)) {
                    NickName nickName = new NickName();
                    nickName.setId(cursor.getLong(cursor.getColumnIndex(ContactsContract.Data._ID)));
                    nickName.setLabel(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Nickname.LABEL)));
                    nickName.setName(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Nickname.NAME)));
                    nickName.setType(cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Nickname.TYPE)));

                    if (TextUtils.isEmpty(nickName.getLabel()) && TextUtils.isEmpty(nickName.getName())) {
                        continue;
                    }
                    contact.addData(nickName);
                } else if (TextUtils.equals(mineType, ContactsContract.CommonDataKinds.SipAddress.CONTENT_ITEM_TYPE)) {
                    SipAddress sipAddress = new SipAddress();
                    sipAddress.setId(cursor.getLong(cursor.getColumnIndex(ContactsContract.Data._ID)));
                    sipAddress.setLabel(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.SipAddress.LABEL)));
                    sipAddress.setSip_address(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.SipAddress.SIP_ADDRESS)));
                    sipAddress.setType(cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.SipAddress.TYPE)));
                    contact.addData(sipAddress);
                } else if (TextUtils.equals(mineType, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)) {
                    StructuredName structuredName = new StructuredName();
                    structuredName.setId(cursor.getLong(cursor.getColumnIndex(ContactsContract.Data._ID)));
                    structuredName.setDisplay_name(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME)));
                    structuredName.setFamily_name(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME)));
                    structuredName.setGiven_name(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME)));
                    structuredName.setMiddle_name(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.MIDDLE_NAME)));
                    structuredName.setPhonetic_family_name(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.PHONETIC_FAMILY_NAME)));
                    structuredName.setPhonetic_given_name(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.PHONETIC_GIVEN_NAME)));
                    structuredName.setPhonetic_middle_name(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.PHONETIC_MIDDLE_NAME)));
                    structuredName.setPrefix(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.PREFIX)));
                    structuredName.setSuffix(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.SUFFIX)));
                    contact.addData(structuredName);
                } else if (TextUtils.equals(mineType, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE)) {
                    StructuredPostal structuredPostal = new StructuredPostal();
                    structuredPostal.setId(cursor.getLong(cursor.getColumnIndex(ContactsContract.Data._ID)));
                    structuredPostal.setType(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.TYPE)));
                    structuredPostal.setCity(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY)));
                    structuredPostal.setCountry(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY)));
                    structuredPostal.setFormatted_address(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.FORMATTED_ADDRESS)));
                    structuredPostal.setLabel(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.LABEL)));
                    structuredPostal.setNeighborhood(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.NEIGHBORHOOD)));
                    structuredPostal.setPobox(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POBOX)));
                    structuredPostal.setPostcode(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE)));
                    structuredPostal.setRegion(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION)));
                    structuredPostal.setStreet(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET)));
                    contact.addData(structuredPostal);
                } else if (TextUtils.equals(mineType, ContactsContract.CommonDataKinds.Relation.CONTENT_ITEM_TYPE)) {
                    Relation relation = new Relation();
                    relation.setId(cursor.getLong(cursor.getColumnIndex(ContactsContract.Data._ID)));
                    relation.setLabel(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Relation.LABEL)));
                    relation.setName(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Relation.NAME)));
                    relation.setType(cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Relation.TYPE)));
                    contact.addData(relation);
                } else if (TextUtils.equals(mineType, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE)) {
                    WebSite webSite = new WebSite();
                    webSite.setId(cursor.getLong(cursor.getColumnIndex(ContactsContract.Data._ID)));
                    webSite.setLabel(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Website.LABEL)));
                    webSite.setUrl(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Website.URL)));
                    webSite.setType(cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Website.TYPE)));
                    contact.addData(webSite);
                } else if (TextUtils.equals(mineType, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE)) {
                    IM im = new IM();
                    im.setId(cursor.getLong(cursor.getColumnIndex(ContactsContract.Data._ID)));
                    im.setLabel(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Im.LABEL)));
                    im.setCustom_protocol(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Im.CUSTOM_PROTOCOL)));
                    im.setType(cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Im.TYPE)));
                    im.setData(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Im.DATA)));
                    im.setProtocol(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Im.PROTOCOL)));
                    contact.addData(im);
                }

            } while (cursor.moveToNext());

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return;
    }


    public List<Group> queryGroup() {
        List<Group> groups = new ArrayList<Group>();


        return groups;
    }


    private void queryData(Uri uri, long cid, IQueryDataListener listener) {
        String selectionArgs[] = {"" + cid};
        Cursor cursor = cr.query(uri, null, ContactsContract.Data.RAW_CONTACT_ID + "=?", selectionArgs, null);

        if (cursor != null) {
            try {
//                Log.d("", "cursor.size=" + cursor.getCount());
                if (cursor.moveToFirst()) {
                    do {
                        listener.onData(cursor);
                    } while (cursor.moveToNext());
                }
            } finally {
                cursor.close();
            }
        }
    }

}
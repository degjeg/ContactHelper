package android.helper.contact.bean;

import com.witness.utils.db.annotation.DBColumn;
import com.witness.utils.db.annotation.DBPrimaryKey;
import com.witness.utils.db.annotation.DBTableName;
import com.witness.utils.db.annotation.DBTransient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danger on 15/3/29.
 */

@DBTableName(name = Contact.Columns.TABLE_NAME_CONTACT)
public class Contact {


    @DBPrimaryKey(name = Columns.CONTACT_ID, autoIncrement = true)
    long cid = 0;

    @DBColumn(name = Columns.DISPLAY_NAME)
    String displayName;

    @DBColumn(name = Columns.STARED)
    int stared;

    @DBTransient
    long raw_contact_id;


    @DBTransient
    int status = 0;//0:初始化 1:加载中 2:加载完成 3:加载失败



/*    List<Email> emailList;
    List<Event> eventList;
    List<IM> imList;
    List<NickName> nickNameList;
    List<Organization> organizationList;
    List<Phone> phoneList;
    List<Photo> photoList;
    List<Relation> relationList;
    List<SipAddress> sipAddressList;
    List<StructuredName> structuredNameList;
    List<StructuredPostal> structuredPostalList;
    List<WebSite> webSiteList;*/

    List<IContactData> dataList = new ArrayList<IContactData>();
    List<Long> groupList;

    public interface Columns {
        public static final String TABLE_NAME_CONTACT = "contact";
        public static final String TABLE_NAME_CONTACT_DATA = "data";


        public static final String _ID = "id";
        public static final String CONTACT_ID = "cid";
        public static final String DISPLAY_NAME = "name";
        public static final String STARED = "stared";

        public static final String DATA_TYPE = "dt";
        public static final String DATA1 = "d1";
        public static final String DATA2 = "d2";
        public static final String DATA3 = "d3";
        public static final String DATA4 = "d4";
        public static final String DATA5 = "d5";
        public static final String DATA6 = "d6";
        public static final String DATA7 = "d7";
        public static final String DATA8 = "d8";
        public static final String DATA9 = "d9";
        public static final String DATA10 = "d10";
        public static final String DATA11 = "d11";
        public static final String DATA12 = "d12";
        public static final String DATA13 = "d13";

    }

    public long getCId() {
        return cid;
    }

    public void setCId(long id) {
        this.cid = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /*public List<Email> getEmailList() {
        return emailList;
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public List<IM> getImList() {
        return imList;
    }

    public List<NickName> getNickNameList() {
        return nickNameList;
    }

    public List<Organization> getOrganizationList() {
        return organizationList;
    }

    public List<Phone> getPhoneList() {
        return phoneList;
    }

    public List<Photo> getPhotoList() {
        return photoList;
    }

    public List<Relation> getRelationList() {
        return relationList;
    }

    public List<SipAddress> getSipAddressList() {
        return sipAddressList;
    }

    public List<StructuredName> getStructuredNameList() {
        return structuredNameList;
    }

    public List<StructuredPostal> getStructuredPostalList() {
        return structuredPostalList;
    }

    public List<WebSite> getWebSiteList() {
        return webSiteList;
    }


    public List<IContactData> getAllData() {
        List<IContactData> list = new ArrayList<IContactData>();

        if (emailList != null) {
            list.addAll(emailList);
        }

        if (eventList != null) {
            list.addAll(eventList);
        }
        if (imList != null) {
            list.addAll(imList);
        }
        if (nickNameList != null) {
            list.addAll(nickNameList);
        }
        if (organizationList != null) {
            list.addAll(organizationList);
        }
        if (phoneList != null) {
            list.addAll(phoneList);
        }
        if (photoList != null) {
            list.addAll(photoList);
        }
        if (relationList != null) {
            list.addAll(relationList);
        }
        if (sipAddressList != null) {
            list.addAll(sipAddressList);
        }
        if (structuredNameList != null) {
            list.addAll(structuredNameList);
        }
        if (structuredPostalList != null) {
            list.addAll(structuredPostalList);
        }
        if (webSiteList != null) {
            list.addAll(webSiteList);
        }

        return list;
    }

    public void addEmail(Email email) {
        if (emailList == null) {
            emailList = new ArrayList();
        }

        emailList.add(email);
    }

    public void addEvent(Event event) {
        if (eventList == null) {
            eventList = new ArrayList();
        }

        eventList.add(event);
    }

    public void addIM(IM im) {
        if (imList == null) {
            imList = new ArrayList();
        }

        imList.add(im);
    }

    public void addNickname(NickName nickName) {
        if (nickNameList == null) {
            nickNameList = new ArrayList();
        }

        nickNameList.add(nickName);
    }

    public void addOrganization(Organization organization) {
        if (organizationList == null) {
            organizationList = new ArrayList();
        }

        organizationList.add(organization);
    }

    public void addPhone(Phone phone) {
        if (phoneList == null) {
            phoneList = new ArrayList();
        }

        phoneList.add(phone);
    }

    public void addPhoto(Photo photo) {
        if (photoList == null) {
            photoList = new ArrayList();
        }

        photoList.add(photo);
    }

    public void addRelation(Relation relation) {
        if (relationList == null) {
            relationList = new ArrayList();
        }

        relationList.add(relation);
    }


    public void addSipAddress(SipAddress sipAddress) {
        if (sipAddressList == null) {
            sipAddressList = new ArrayList();
        }

        sipAddressList.add(sipAddress);
    }

    public void addStructuredName(StructuredName structuredName) {
        if (structuredNameList == null) {
            structuredNameList = new ArrayList();
        }

        structuredNameList.add(structuredName);
    }

    public void addStructuredPostal(StructuredPostal structuredPostal) {
        if (structuredPostalList == null) {
            structuredPostalList = new ArrayList();
        }

        structuredPostalList.add(structuredPostal);
    }

    public void addWebSite(WebSite webSite) {
        if (webSiteList == null) {
            webSiteList = new ArrayList();
        }

        webSiteList.add(webSite);
    }*/

    public void addData(IContactData data) {
        dataList.add(data);
    }

    public List<IContactData> getDatas() {
        return dataList;
    }

    public void addGroup(Long gid) {
        if (gid == null || gid == 0) {
            return;
        }
        if (groupList == null) {
            groupList = new ArrayList<Long>();
        }
        if (!groupList.contains(gid)) {
            groupList.add(gid);
        }
    }

    public List<Long> getGroupList() {
        return groupList;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

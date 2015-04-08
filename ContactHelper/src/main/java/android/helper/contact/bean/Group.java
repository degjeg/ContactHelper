package android.helper.contact.bean;

import com.witness.utils.db.annotation.DBColumn;
import com.witness.utils.db.annotation.DBPrimaryKey;
import com.witness.utils.db.annotation.DBTableName;

/**
 * Created by danger on 15/3/31.
 */

@DBTableName(name = "group")
public class Group {

    @DBColumn(name = "name")
    String groupName;

    @DBPrimaryKey(name = "id", autoIncrement = true)
    long gid;


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public long getGid() {
        return gid;
    }

    public void setGid(long gid) {
        this.gid = gid;
    }
}

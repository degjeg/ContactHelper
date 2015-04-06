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

}

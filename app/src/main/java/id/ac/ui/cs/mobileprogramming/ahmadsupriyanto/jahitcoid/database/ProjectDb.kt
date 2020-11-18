package id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.database;

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.ac.ui.cs.mobileprogramming.ahmadsupriyanto.jahitcoid.Constant

@Entity(tableName = Constant.Table.PROJECT)
data class ProjectDb (
    @PrimaryKey var id: String,
    @ColumnInfo(name="project_name") val name: String?,
    @ColumnInfo(name="project_category") val category: String?,
    @ColumnInfo(name="project_price") val price: String?,
    @ColumnInfo(name="project_amount") val amount: String?,
    @ColumnInfo(name="project_address") val address: String?,
    @ColumnInfo(name="project_note") val note: String?,
    @ColumnInfo(name="project_preview") val preview: String?,
    @ColumnInfo(name="project_status") val status: String?,
    @ColumnInfo(name="project_annotation") val annotation: String?
)
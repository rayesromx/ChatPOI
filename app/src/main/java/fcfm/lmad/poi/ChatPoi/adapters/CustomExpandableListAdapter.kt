package fcfm.lmad.poi.ChatPoi.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import fcfm.lmad.poi.ChatPoi.R
import fcfm.lmad.poi.ChatPoi.TeamActivity
import fcfm.lmad.poi.ChatPoi.domain.entities.Team
import fcfm.lmad.poi.ChatPoi.domain.entities.TeamContainer

class CustomExpandableListAdapter internal constructor(
    private val context: Context,
    private val teamList: List<TeamContainer>
) : BaseExpandableListAdapter() {

    override fun getChild(listPosition: Int, expandedListPosition: Int): Any {
        return this.teamList[listPosition].childTeams!![expandedListPosition]
    }
    override fun getChildId(listPosition: Int, expandedListPosition: Int): Long {
        return expandedListPosition.toLong()
    }
    override fun getChildView(
        listPosition: Int,
        expandedListPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var convertView = convertView
        val team = getChild(listPosition, expandedListPosition) as Team
        if (convertView == null) {
            val layoutInflater =
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.item_view_team, null)
        }
        val expandedListTextView = convertView!!.findViewById<TextView>(R.id.team_item_name)
        expandedListTextView.text = team.name

        convertView.setOnClickListener{
            val intent = Intent(context,TeamActivity::class.java)
            intent.putExtra("team_id",team.id)
            intent.putExtra("team_name",team.name)
            context.startActivity(intent)
        }

        return convertView
    }
    override fun getChildrenCount(listPosition: Int): Int {
        return this.teamList[listPosition].childTeams!!.size
    }
    override fun getGroup(listPosition: Int): Any {
        return this.teamList[listPosition]
    }
    override fun getGroupCount(): Int {
        return this.teamList.size
    }
    override fun getGroupId(listPosition: Int): Long {
        return listPosition.toLong()
    }
    override fun getGroupView(
        listPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var convertView = convertView
        val listTitle = getGroup(listPosition) as TeamContainer
        if (convertView == null) {
            val layoutInflater =
                this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.item_view_team_container, null)
        }
        val listTitleTextView = convertView!!.findViewById<TextView>(R.id.team_item_container_title)
        listTitleTextView.text = listTitle.team?.name
        return convertView
    }
    override fun hasStableIds(): Boolean {
        return false
    }
    override fun isChildSelectable(listPosition: Int, expandedListPosition: Int): Boolean {
        return true
    }
}
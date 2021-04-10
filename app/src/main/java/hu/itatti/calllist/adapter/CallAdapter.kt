package hu.itatti.calllist.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.itatti.calllist.data.Call
import hu.itatti.calllist.databinding.CallRowBinding

class CallAdapter(callList: List<Call>) : RecyclerView.Adapter<CallAdapter.ViewHolder>() {
    private var listItems = mutableListOf<Call>()

    init {
        listItems.addAll(callList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val binding= CallRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

   inner class ViewHolder(binding: CallRowBinding): RecyclerView.ViewHolder(binding.root) {
        var tvPhonenumber = binding.tvNum
        var tvDate = binding.tvDate
        var btnCall = binding.btnCall
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val callItem = listItems[position]

        holder.tvPhonenumber.text = callItem.phoneNum
        holder.tvDate.text = callItem.date
        holder.btnCall.setOnClickListener {
            val context = holder.btnCall.context

            val intentPackage = Intent(Intent.ACTION_CALL, Uri.parse("tel:${holder.tvPhonenumber.text}"))
            context.startActivity(intentPackage)

        }
    }

    override fun getItemCount(): Int {
        return listItems.size
    }
}
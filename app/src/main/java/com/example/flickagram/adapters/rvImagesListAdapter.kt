package com.example.flickagram.adapters

import android.content.Context
import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flickagram.Model.Hit
import com.example.flickagram.databinding.ImagelistViewItemsBinding


class rvImagesListAdapter(
    val context: Context,
    var ImgList: MutableList<Hit>,
    private val listener: ClickedImage
) :
    RecyclerView.Adapter<rvImagesListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view =
            ImagelistViewItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewholder = ViewHolder(view)

        view.root.setOnClickListener {
            listener.ClickedImage(ImgList[viewholder.adapterPosition].largeImageURL,ImgList[viewholder.adapterPosition].tags,ImgList)
            d("mytag", it.toString())
        }
        return viewholder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val url = ImgList[position].largeImageURL
        val Name = ImgList[position].tags

        Glide.with(context).load(url).into(holder.iv_Image)
        holder.tv_ImageName.text = Name
        holder.itemView.setOnClickListener {
            listener.ClickedImage(url, Name,ImgList)

        }

    }

    override fun getItemCount(): Int {
        return ImgList.size
    }

    class ViewHolder(binding: ImagelistViewItemsBinding) : RecyclerView.ViewHolder(binding.root) {

        val tv_ImageName = binding.tvImageName
        var iv_Image = binding.ivImage

    }
}

interface ClickedImage {
    fun ClickedImage(url: String, name: String,list:List<Hit>) {}
}

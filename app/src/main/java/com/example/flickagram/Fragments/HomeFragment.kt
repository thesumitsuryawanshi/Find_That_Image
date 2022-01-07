package com.example.flickagram.Fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flickagram.Model.Hit
import com.example.flickagram.R
import com.example.flickagram.ViewModel.ViewModelFactory
import com.example.flickagram.ViewModel.mainViewModel
import com.example.flickagram.adapters.ClickedImage
import com.example.flickagram.adapters.rvImagesListAdapter
import com.example.flickagram.databinding.FragmentHomeBinding
import com.example.flickagram.repository.repository
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(), ClickedImage {


    @Inject
    lateinit var repository: repository


    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setHasOptionsMenu(true)
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(repository)
        ).get(mainViewModel::class.java)

        binding.ivSearchbtn.setOnClickListener {

            val searchKeyword = binding.etSearch.text.toString()
            mainViewModel.getIamgesList(searchKeyword)

        }
        mainViewModel.ImagesList.observe(viewLifecycleOwner) {
            passImagestoAdapter(it.hits)
        }

    }

    private fun passImagestoAdapter(list: List<Hit>) {
        val list2: MutableList<Hit> = mutableListOf()


        for (i in list.indices) {
            list2.add(list[i])
        }


        val adapter = rvImagesListAdapter(requireContext(), list2, this)


        binding.rvImagesList.adapter = adapter
        binding.rvImagesList.layoutManager =
            GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.mi_gridView -> {
                binding.rvImagesList.layoutManager = GridLayoutManager(context, 2)
            }

            R.id.mi_listView -> {
                binding.rvImagesList.layoutManager = LinearLayoutManager(context)
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun ClickedImage(url: String, name: String, list: List<Hit>) {

        val ImageList = arrayListOf<String>()

        for (i in 0..list.size - 1) {
            ImageList.add(list[i].largeImageURL)
        }

        val bundle = Bundle()
        bundle.putStringArrayList("list", ImageList)
        bundle.putString("url", url)
        bundle.putString("name", name)

        findNavController().navigate(R.id.action_homeFragment_to_detailViewFragment, bundle)

    }
}



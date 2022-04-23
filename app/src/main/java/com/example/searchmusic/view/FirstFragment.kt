package com.example.searchmusic.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instabugandroidchallenge.network.CheckInternetConnection
import com.example.searchmusic.R
import com.example.searchmusic.adapter.MusicSearchAdapter
import com.example.searchmusic.databinding.FragmentFirstBinding
import com.example.searchmusic.view_model.MusicViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    private var adapter = MusicSearchAdapter(mutableListOf())

    private val viewModel: MusicViewModel by lazy {
        ViewModelProvider(this).get(MusicViewModel::class.java)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindView()
        if (CheckInternetConnection.isOnline(this)) {
            fetchData()
        } else {
            handleOfflineMode()
        }
        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun handleOfflineMode() {
        WarningDialog.showErrorDialog(requireActivity())
    }

    private fun bindView() {
        initRecyclerview()
    }

    private fun initRecyclerview() {
        binding.recyclerviewList.adapter = adapter
        binding.recyclerviewList.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerviewList.setHasFixedSize(false)
    }

    private fun fetchData() {
        Thread {
            showProgressBar()
//            val htmlText = WordsCountPresenter.newInstance().fetchData()
            requireActivity().runOnUiThread {
                addList(htmlText)
                hideProgressBar()
            }
        }.start()
    }

    private fun addList(htmlText: String?) {
        val finalList = htmlText?.let { ListUtilities.mapResponse(it) }
        finalList?.let {
            adapter.addList(it)
            adapter.updateMyList(it)
        }
    }

    private fun showProgressBar() {
        binding.progressBarLayout.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBarLayout.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
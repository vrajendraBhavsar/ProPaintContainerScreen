package com.example.propaintcontainerscreen.presentation.mypaint

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.propaintcontainerscreen.R
import com.example.propaintcontainerscreen.data.PaintContainer
import com.example.propaintcontainerscreen.databinding.FragmentMyPaintsBinding
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.io.IOException

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MyPaintsFragment : Fragment() {

    private var _binding: FragmentMyPaintsBinding? = null
    private var itemQuantity: Int = 1
    private var containerAmount: Int = 0

    private val myPaintsViewModel by lazy {
        ViewModelProvider(
            requireActivity()
        )[MyPaintsViewModel::class.java]
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMyPaintsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.ivArt.setOnClickListener {
            Toast.makeText(requireContext(), "${myPaintsViewModel.getContainerData(requireContext())}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val containerData = myPaintsViewModel.getContainerData(requireContext())
        //name
        binding.tvContainerName.text = containerData[0].name
        //price
//        containerAmount = containerData[0].colors[0].amount
        binding.tvContainerPrice.text =  resources.getString(
            R.string.paint_amount,
            containerAmount
        )
        //Sheen
        binding.rbSheen1.text = containerData[0].colors[0].sheen[0].name
        binding.rbSheen2.text = containerData[0].colors[0].sheen[1].name
        binding.rbSheen3.text = containerData[0].colors[0].sheen[2].name
        //Container size
        binding.rbContainerSize1.text = containerData[0].colors[0].size
        binding.rbContainerSize2.text = containerData[0].colors[1].size

        binding.rgContainerSize.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rbContainerSize1 -> {
                    binding.tvContainerPrice.text = resources.getString(
                        R.string.paint_amount,
                        containerData[0].colors[0].amount
                    )
                    binding.rgSheen.visibility = View.VISIBLE
                    containerAmount = containerData[0].colors[0].amount
                    itemQuantity = 1
                    binding.etQuantity.text = itemQuantity.toString()
                }

                R.id.rbContainerSize2 -> {
                    binding.tvContainerPrice.text = resources.getString(
                        R.string.paint_amount,
                        containerData[0].colors[1].amount
                    )
                    binding.rgSheen.visibility = View.INVISIBLE
                    containerAmount = containerData[0].colors[1].amount
                    itemQuantity = 1
                    binding.etQuantity.text = itemQuantity.toString()
                }
            }
        }


            binding.btnIncrement.setOnClickListener {
                if (itemQuantity > 0) {
                    itemQuantity += 1
                    binding.etQuantity.text = itemQuantity.toString()
                    binding.tvContainerPrice.text =  resources.getString(
                        R.string.paint_amount,
                        itemQuantity * containerAmount
                    )
                }
            }

            binding.btnDecrement.setOnClickListener {
                itemQuantity -= 1
                if (itemQuantity > 0) {
                    binding.etQuantity.text = itemQuantity.toString()
                    binding.tvContainerPrice.text =  resources.getString(
                        R.string.paint_amount,
                        itemQuantity * containerAmount
                    )
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
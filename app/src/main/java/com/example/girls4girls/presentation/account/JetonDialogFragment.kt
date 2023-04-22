package com.example.girls4girls.presentation.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.girls4girls.R
import com.example.girls4girls.data.model.Jeton
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class JetonDialogFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.jeton_dialog_box, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardInfo = arguments?.getSerializable("cardInfo") as? Jeton

        val image = view.findViewById<ImageView>(R.id.jetonImage)

        if (cardInfo != null) {
            view.findViewById<TextView>(R.id.jetonDescription).text = cardInfo.cardInfo.info
            Glide.with(requireActivity()).load(cardInfo.image.url).into(image)
            view.findViewById<TextView>(R.id.jetonName).text = cardInfo.title
        }

        image.setOnClickListener {
            dismiss()
        }
    }
}
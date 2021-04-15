package br.com.tavieto.empresas

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide

class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()

    private lateinit var nameCompanyView: AppCompatTextView
    private lateinit var imageView: AppCompatImageView
    private lateinit var descriptionView: AppCompatTextView
    private lateinit var toolbar : Toolbar

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameCompanyView = view.findViewById(R.id.txtNameCompany)
        descriptionView = view.findViewById(R.id.txtDescription)
        imageView = view.findViewById(R.id.imageCompany)
        toolbar = view.findViewById(R.id.navToolbarDetails)

        setupToolbar()
        configureView()
    }

    private fun configureView() {
        nameCompanyView.text = args.name
        descriptionView.text = args.description
        descriptionView.movementMethod = ScrollingMovementMethod()
        setImageCompany()
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity).run {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setImageCompany() {
        Glide
                .with(this)
                .load(args.pathImage)
                .centerCrop()
                .placeholder(R.drawable.img_logo)
                .into(imageView)
    }
}
package com.davidups.starwars.features.person.view.fragments

import android.os.Bundle
import android.view.View
import com.davidups.skell.R
import com.davidups.skell.databinding.FragmentPersonBinding
import com.davidups.starwars.core.extensions.*
import com.davidups.starwars.core.platform.BaseFragment
import com.davidups.starwars.core.platform.viewBinding.viewBinding
import com.davidups.starwars.features.people.models.view.PersonDetailView

class PersonFragment : BaseFragment(R.layout.fragment_person) {

    private val binding by viewBinding(FragmentPersonBinding::bind)

    private val personDetail: PersonDetailView? by lazy { arguments?.getParcelable<PersonDetailView>(PERSON_KEY) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        binding.apply {
            ivBanner.loadFromUrl(String.randomImage())
            tvName.text = personDetail?.name
            tvBirthYear.text = personDetail?.birthYear
            tvGender.text = personDetail?.gender
            tvHeight.text = personDetail?.height
        }
    }

    companion object {
        const val PERSON_KEY = "personKey"
    }
}

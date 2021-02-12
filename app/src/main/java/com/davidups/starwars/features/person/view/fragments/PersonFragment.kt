package com.davidups.starwars.features.person.view.fragments

import android.os.Bundle
import android.view.View
import com.davidups.skell.R
import com.davidups.skell.databinding.FragmentPersonBinding
import com.davidups.starwars.core.extensions.*
import com.davidups.starwars.core.platform.BaseFragment
import com.davidups.starwars.core.platform.viewBinding.viewBinding
import com.davidups.starwars.features.people.models.view.PersonDetail
import com.davidups.starwars.features.person.view.viewmodels.PersonViewModel
import kotlinx.android.synthetic.main.fragment_person.*
import org.koin.android.ext.android.inject

class PersonFragment : BaseFragment(R.layout.fragment_person) {

    private val binding by viewBinding(FragmentPersonBinding::bind)

    private val personViewModel: PersonViewModel by inject()

    private val personDetail: PersonDetail? by lazy { arguments?.getParcelable<PersonDetail>(PERSON_KEY) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(personViewModel) {
            observe(isFavorite, ::onFavoriteChange)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        personViewModel.checkIfFavorite(personDetail)

        initView()
    }

    private fun initView() {
        binding.apply {
            ivBanner.loadFromUrl(String.randomImage())
            tvName.text = personDetail?.name
            tvBirthYear.text = personDetail?.birthYear
            tvGender.text = personDetail?.gender
            tvHeight.text = personDetail?.height
            tvFavorite.onClick {
                personDetail?.isFavorite?.let { isFav ->
                    if (isFav) personViewModel.deleteFavorite(personDetail) else personViewModel.saveFavorite(personDetail)
                }
            }
        }
    }

    private fun onFavoriteChange(isFav: Boolean?) {
        binding.tvFavorite.text = if (isFav!!) "Remove from favorite" else "Save as favorite"
    }

    companion object {
        const val PERSON_KEY = "personKey"
    }
}

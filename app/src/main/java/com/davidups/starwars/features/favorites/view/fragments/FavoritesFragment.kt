package com.davidups.starwars.features.favorites.view.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.davidups.skell.R
import com.davidups.skell.databinding.FragmentMoviesBinding
import com.davidups.skell.databinding.FragmentPeopleBinding
import com.davidups.starwars.core.extensions.failure
import com.davidups.starwars.core.extensions.observe
import com.davidups.starwars.core.extensions.onClick
import com.davidups.starwars.core.extensions.showInfoAlertDialog
import com.davidups.starwars.core.platform.BaseFragment
import com.davidups.starwars.core.platform.viewBinding.viewBinding
import com.davidups.starwars.features.people.models.view.PeopleView
import com.davidups.starwars.features.people.models.view.PersonDetail
import com.davidups.starwars.features.people.models.view.PersonView
import com.davidups.starwars.features.people.view.adapters.PeopleAdapter
import com.davidups.starwars.features.people.view.viewmodels.PeopleViewModel
import com.davidups.starwars.features.person.view.fragments.PersonFragment
import com.kotlinpermissions.notNull
import kotlinx.android.synthetic.main.navigation_activity.*
import org.koin.android.ext.android.inject

class FavoritesFragment : BaseFragment(R.layout.fragment_movies) {

    private val binding by viewBinding(FragmentMoviesBinding::bind)

    private val peopleViewModel: PeopleViewModel by inject()
    private val peopleAdapter: PeopleAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(peopleViewModel) {
            observe(favoritePeople, ::handleFavorites)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListeners()
    }

    private fun initView() {
        peopleViewModel.getFavorites()

        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = peopleAdapter
        }
    }

    private fun initListeners() {
        peopleAdapter.clickListener = {
            val personDetail: PersonDetail? = peopleViewModel.favoritePeople.value?.find { person -> person.name == it.name }
            val bundle = bundleOf(PersonFragment.PERSON_KEY to personDetail)
            view?.findNavController()?.navigate(R.id.Person, bundle)
        }
    }

    private fun handleFavorites(favorites: List<PersonDetail>?) {
        favorites.notNull { fav ->
            peopleAdapter.collection =  fav.map { it.toPersonView() }.toMutableList()
        }
    }
}

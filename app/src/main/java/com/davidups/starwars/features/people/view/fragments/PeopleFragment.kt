package com.davidups.starwars.features.people.view.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.davidups.skell.R
import com.davidups.skell.databinding.FragmentMoviesBinding
import com.davidups.starwars.core.extensions.failure
import com.davidups.starwars.core.extensions.observe
import com.davidups.starwars.core.extensions.showInfoAlertDialog
import com.davidups.starwars.core.platform.BaseFragment
import com.davidups.starwars.core.platform.viewBinding.viewBinding
import com.davidups.starwars.features.people.models.view.PeopleView
import com.davidups.starwars.features.people.models.view.PersonDetail
import com.davidups.starwars.features.people.view.adapters.PeopleAdapter
import com.davidups.starwars.features.people.view.viewmodels.PeopleViewModel
import com.davidups.starwars.features.person.view.fragments.PersonFragment
import com.kotlinpermissions.notNull
import org.koin.android.ext.android.inject

class PeopleFragment : BaseFragment(R.layout.fragment_movies) {

    private val binding by viewBinding(FragmentMoviesBinding::bind)

    private val peopleViewModel: PeopleViewModel by inject()
    private val peopleAdapter: PeopleAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(peopleViewModel) {
            observe(showSpinner, ::handleShowSpinner)
            observe(peopleView, ::handleMovies)
            failure(failure, ::handleFailure)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListeners()
    }

    private fun initView() {
        peopleViewModel.getPeople()

        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(requireActivity(), 2)
            adapter = peopleAdapter
        }
    }

    private fun initListeners() {
        peopleAdapter.clickListener = {
            val personDetail: PersonDetail? = peopleViewModel.people.value?.results?.find {
                    person -> person.name == it.name
            }?.toPersonDetail()
            val bundle = bundleOf(PersonFragment.PERSON_KEY to personDetail)
            view?.findNavController()?.navigate(R.id.Person, bundle)
        }
    }

    private fun handleMovies(people: PeopleView?) {
        people.notNull { movies ->
            peopleAdapter.collection = movies.results.orEmpty()
        }
    }

    private fun handleShowSpinner(show: Boolean?) {
        showSpinner(show ?: false)
    }

    private fun handleFailure(failure: Throwable?) {
        showInfoAlertDialog {
            setTitle(getString(R.string.common_error))
        }.show()
    }
}

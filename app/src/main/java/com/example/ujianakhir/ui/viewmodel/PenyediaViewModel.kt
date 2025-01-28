package com.example.ujianakhir.ui.viewmodel


import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.ujianakhir.TerapiApplications
import com.example.ujianakhir.ui.viewmodel.jenisterapi.DetailJenisTerapiViewModel
import com.example.ujianakhir.ui.viewmodel.jenisterapi.HomeJenisTerapiViewModel
import com.example.ujianakhir.ui.viewmodel.jenisterapi.InsertJenisTerapiViewModel
import com.example.ujianakhir.ui.viewmodel.jenisterapi.UpdateJenisTerapiViewModel
import com.example.ujianakhir.ui.viewmodel.pasienVM.DetailPasienViewModel
import com.example.ujianakhir.ui.viewmodel.pasienVM.HomePasienViewModel
import com.example.ujianakhir.ui.viewmodel.pasienVM.InsertPasienViewModel
import com.example.ujianakhir.ui.viewmodel.pasienVM.UpdatePasienViewModel
import com.example.ujianakhir.ui.viewmodel.sesiterapi.DetailSesiTerapiViewModel
import com.example.ujianakhir.ui.viewmodel.sesiterapi.HomeSesiTerapiViewModel
import com.example.ujianakhir.ui.viewmodel.sesiterapi.InsertSesiTerapiViewModel
import com.example.ujianakhir.ui.viewmodel.sesiterapi.UpdateSesiTerapiViewModel
import com.example.ujianakhir.ui.viewmodel.terapisVM.DetailTerapisViewModel
import com.example.ujianakhir.ui.viewmodel.terapisVM.HomeTerapisViewModel
import com.example.ujianakhir.ui.viewmodel.terapisVM.InsertTerapisViewModel
import com.example.ujianakhir.ui.viewmodel.terapisVM.UpdateTerapisViewModel


object PenyediaViewModel {
    val Factory = viewModelFactory {
        initializer { HomePasienViewModel(aplikasiPasien().container.pasienRepository) }
        initializer { InsertPasienViewModel(aplikasiPasien().container.pasienRepository) }
        initializer { DetailPasienViewModel(aplikasiPasien().container.pasienRepository) }
        initializer { UpdatePasienViewModel(createSavedStateHandle(), aplikasiPasien().container.pasienRepository) }
        //initializer { PasienSesiTerapiViewModel(createSavedStateHandle(), aplikasiPasien().container.pasienRepository) }

        initializer { HomeTerapisViewModel(aplikasiPasien().container.terapisRepository) }
        initializer { InsertTerapisViewModel(aplikasiPasien().container.terapisRepository) }
        initializer { UpdateTerapisViewModel(createSavedStateHandle(), aplikasiPasien().container.terapisRepository) }
        initializer { DetailTerapisViewModel(aplikasiPasien().container.terapisRepository) }

        initializer { HomeJenisTerapiViewModel(aplikasiPasien().container.jenisTerapiRepository) }
        initializer { InsertJenisTerapiViewModel(aplikasiPasien().container.jenisTerapiRepository) }
        initializer { DetailJenisTerapiViewModel(aplikasiPasien().container.jenisTerapiRepository) }
        initializer { UpdateJenisTerapiViewModel(createSavedStateHandle(), aplikasiPasien().container.jenisTerapiRepository) }

        initializer { HomeSesiTerapiViewModel(aplikasiPasien().container.sesiTerapiRepository) }
        initializer { InsertSesiTerapiViewModel(aplikasiPasien().container.sesiTerapiRepository) }
        initializer { UpdateSesiTerapiViewModel(createSavedStateHandle(), aplikasiPasien().container.sesiTerapiRepository) }
        initializer { DetailSesiTerapiViewModel(aplikasiPasien().container.sesiTerapiRepository) }
    }
}

fun CreationExtras.aplikasiPasien(): TerapiApplications =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TerapiApplications)

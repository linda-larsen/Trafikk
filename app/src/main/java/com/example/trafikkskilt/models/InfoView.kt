package com.example.trafikkskilt.models

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.trafikkskilt.constants.paddingSize

@Composable
fun InfoView(){
    Column{
        //Headline
        Text(
            //TODO: Putte strings inn i strings.xml (ikke gjort fordi merge conflicts fra helvete
            text = "Informasjon om applikasjonen",
            modifier = Modifier.padding(paddingSize),
        )

        //Info
        Text(
            //TODO: Putte strings inn i strings.xml (ikke gjort fordi merge conflicts fra helvete
            text = "Trafikkskilt er en applikasjon som skal registrere trafikkskilt mens du kjører forbi."
                    + "\nFor å ta i bruk Trafikkskilt må du gjennom tre steg:",
            modifier = Modifier.padding(paddingSize),
            )
        Text(
            //TODO: Putte strings inn i strings.xml (ikke gjort fordi merge conflicts fra helvete

            text = "1. Kalibrer kameraet.",
            modifier = Modifier.padding(paddingSize),
            )

        Text(text =
            //TODO: Putte strings inn i strings.xml (ikke gjort fordi merge conflicts fra helvete
            "Du finner denne funksjonen i hamburgermenyen oppe til høyre."
                + "\n  Sørg for at telefonen er plassert slik at kameraet fanger opp veiskulderen og trafikkskilt."
                + "\n  Ikke kalibrer kameraet mens du kjører.",
            modifier = Modifier.padding(paddingSize),
            )
        Text(
            //TODO: Putte strings inn i strings.xml (ikke gjort fordi merge conflicts fra helvete
            text = "2. Gå til startskjermen og start kjøretur",
            modifier = Modifier.padding(paddingSize),
        )
    }
}
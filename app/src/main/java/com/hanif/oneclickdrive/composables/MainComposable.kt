package com.hanif.oneclickdrive.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hanif.oneclickdrive.composables.common.CommonTextFiled
import com.hanif.oneclickdrive.utils.stringToSet

@Preview
@Composable
fun MainComposable() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ThreeTextBoxes()
    }
}

@Composable
fun ThreeTextBoxes() {

    var firstTextFiled by remember {
        mutableStateOf("")
    }

    var secondTextFiled by remember {
        mutableStateOf("")
    }

    var thirdTextFiled by remember {
        mutableStateOf("")
    }

    var intersection by remember {
        mutableStateOf("")
    }
    var union by remember {
        mutableStateOf("")
    }
    var maxNum by remember {
        mutableStateOf("")
    }
    
    var isError by remember {
        mutableStateOf(false)
    }


    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CommonTextFiled(
            text = firstTextFiled,
            hint = "Enter list of digits using comma secprated",
            onValueChange = {
                firstTextFiled = it
            }, imeAction = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(16.dp))
        CommonTextFiled(
            text = secondTextFiled,
            hint = "Enter list of digits using comma secprated",
            onValueChange = {
                secondTextFiled = it
            },
            imeAction = ImeAction.Next
        )
        Spacer(modifier = Modifier.height(16.dp))
        CommonTextFiled(
            text = thirdTextFiled,
            hint = "Enter list of digits using comma secprated",
            onValueChange = {
                thirdTextFiled = it
            },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (firstTextFiled.isNotEmpty() && secondTextFiled.isNotEmpty() && thirdTextFiled.isNotEmpty()) {
                    val list1 = firstTextFiled.stringToSet()
                    val list2 = secondTextFiled.stringToSet()
                    val list3 = thirdTextFiled.stringToSet()
                    intersection = list1.intersect(list2).intersect(list3).joinToString(", ")
                    union = list1.union(list2).union(list3).joinToString(", ")
                    maxNum = (list1 + list2 + list3).maxOrNull().toString()

                    isError = false
                }else{
                    isError = true
                }
            },
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text("Calculate")
        }

        Spacer(modifier = Modifier.height(16.dp))
        
        AnimatedVisibility(visible = isError) {
            Text(text = "Please Fill all the filed.", color = Color.Red)
        }

        AnimatedVisibility(visible = intersection.isNotEmpty() && union.isNotEmpty() && maxNum.isNotEmpty()) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Intersect: $intersection")
                Text("Union: $union")
                Text("Highest Number: $maxNum")
            }
        }
    }
}




package com.example.xpense.presentation.add_edit_transaction

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.xpense.presentation.common.transactionTypes

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddEditTransaction(
    navHostController: NavHostController,
    transactionId:Int,
    previousScreen:String,
    viewModel: AddEditTransactionViewModel = hiltViewModel()
) {
    BackHandler {
        viewModel.onEvent(AddEditTransactionEvent.OpenDialog)
    }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .background(
                Brush.verticalGradient(
                colors = listOf(Color(0xFF3ABBB9), Color.White)
            ))
            .fillMaxSize()
            .padding(8.dp),
    ) {


        Spacer(modifier = Modifier.height(32.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = {
                    viewModel.onEvent(AddEditTransactionEvent.OpenDialog)

                },
            ) {
                Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Back")
            }

            Text(text = "Transactions", fontSize = 20.sp, color = Color(0xFF2A7C76))
            Spacer(modifier = Modifier.width(36.dp))


        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Add Expense",
                fontSize = 16.sp
            )
        }

        if (viewModel.dialogState.value.state) {
            AlertDialog(
                onDismissRequest = {
                    viewModel.onEvent(AddEditTransactionEvent.CloseDialog)
                },
                buttons = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Button(
                            onClick = {
                                navHostController.navigateUp()
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFF243D25)
                            )
                        ) {
                            Text(
                                text = "Yes!",
                                color = Color.White
                            )
                        }
                    }
                },
                text = {
                    Text(text = viewModel.dialogState.value.text)
                })
        }

        Spacer(modifier = Modifier.height(24.dp))
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF2A7C76))
                    .padding(24.dp, 32.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = viewModel.title.value.text,
                    onValueChange = { viewModel.onEvent(AddEditTransactionEvent.EnteredTitle(it)) },
                    modifier = Modifier.width(280.dp),
                    placeholder = {
                        Text(
                            text = viewModel.title.value.hint,
                            modifier = Modifier.alpha(0.5f)
                        )
                    },
                    shape = RoundedCornerShape(9.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        backgroundColor = Color.White
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = viewModel.amount.value.text,
                    onValueChange = { viewModel.onEvent(AddEditTransactionEvent.EnteredAmount(it)) },
                    modifier = Modifier.width(280.dp),
                    placeholder = {
                        Text(
                            text = viewModel.amount.value.hint,
                            modifier = Modifier.alpha(0.5f)
                        )
                    },
                    shape = RoundedCornerShape(9.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        backgroundColor = Color.White
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Decimal
                    ),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(16.dp))
                ExposedDropdownMenuBox(
                    expanded = viewModel.transactionType.value.isExpanded,
                    onExpandedChange = {
                        viewModel.onEvent(AddEditTransactionEvent.OnExpandedChange)
                    },
                    modifier = Modifier.width(280.dp)
                ) {
                    TextField(
                        placeholder = {
                            Text(
                                text = viewModel.transactionType.value.hint,
                                modifier = Modifier.alpha(0.5f)
                            )
                        },
                        readOnly = true,
                        value = viewModel.transactionType.value.selectedOption,
                        onValueChange = { },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = viewModel.transactionType.value.isExpanded
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            cursorColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
                    ExposedDropdownMenu(
                        expanded = viewModel.transactionType.value.isExpanded,
                        onDismissRequest = {
                            viewModel.onEvent(AddEditTransactionEvent.OnDismissRequest)
                        }
                    ) {
                        transactionTypes.forEach { selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    viewModel.onEvent(
                                        AddEditTransactionEvent.ChangeSelectedOption(
                                            selectionOption
                                        )
                                    )
                                    viewModel.onEvent(AddEditTransactionEvent.OnDismissRequest)
                                }
                            ) {
                                Text(text = selectionOption)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = viewModel.tags.value.text,
                    onValueChange = { viewModel.onEvent(AddEditTransactionEvent.EnteredTags(it)) },
                    modifier = Modifier.width(280.dp),
                    placeholder = {
                        Text(
                            text = viewModel.tags.value.hint,
                            modifier = Modifier.alpha(0.5f)
                        )
                    },
                    shape = RoundedCornerShape(9.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        backgroundColor = Color.White
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = viewModel.note.value.text,
                    onValueChange = { viewModel.onEvent(AddEditTransactionEvent.EnteredNote(it)) },
                    modifier = Modifier.width(280.dp),
                    placeholder = {
                        Text(
                            text = viewModel.note.value.hint,
                            modifier = Modifier.alpha(0.5f)
                        )
                    },
                    shape = RoundedCornerShape(9.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        backgroundColor = Color.White
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true
                )
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = {
                        viewModel.onEvent(
                            AddEditTransactionEvent.SaveEditTransaction(
                                context,
                                navHostController
                            )
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF243D25),
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "SAVE")
                }


            }

        }
    }
}



package com.example.xpense.presentation.util

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.xpense.presentation.about.About
import com.example.xpense.presentation.add_edit_transaction.AddEditTransaction
import com.example.xpense.presentation.dashboard.Dashboard
import com.example.xpense.presentation.profile.Profile
import com.example.xpense.presentation.transaction_details.TransactionDetails
import com.example.xpense.presentation.transactions.Transactions
import com.example.xpense.presentation.wallet.Wallet

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SetupNavigation() {
    val navController = rememberNavController()


    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute == Screen.Dashboard.route || currentRoute == Screen.Transactions.route|| currentRoute==Screen.WalletScreen.route ||currentRoute==Screen.ProfileScreen.route) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.BottomCenter
                ) {

                    BottomBar(navController = navController)
                    Column {
                        FloatingActionButton(onClick = {
                            navController.navigate(Screen.AddEditTransaction.route + "/-1" + "/null")
                        }, backgroundColor = Color(0xFF243D25)) {
                            Icon(
                                imageVector = Icons.Outlined.Add,
                                contentDescription = "Add Transactions",
                                tint = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }

                }
            }
        },

        ) {
        NavigationGraph(
            navController = navController,
        )
    }
}

@Composable
fun NavigationGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.Dashboard.route) {

        composable(
            route = Screen.Dashboard.route,
        ) {
            Dashboard(navController)
        }

        composable(
            route = Screen.AddEditTransaction.route + "/{id}/{previousScreen}",
            arguments = listOf(
                navArgument(
                    name = "id"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                },
                navArgument(
                    name = "previousScreen"
                ) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {
            val transactionId = it.arguments?.getInt("transactionId") ?: -1
            val previousScreen = it.arguments?.getString("previousScreen") ?: ""

            AddEditTransaction(
                navHostController = navController,
                transactionId = transactionId,
                previousScreen = previousScreen
            )
        }

        composable(
            route = Screen.TransactionDetails.route + "/{transactionId}",
            arguments = listOf(
                navArgument(
                    name = "transactionId"
                ) {
                    type = NavType.IntType
                    defaultValue = -1
                })
        ) {
            val transactionId = it.arguments?.getInt("transactionId") ?: -1

            TransactionDetails(navHostController = navController, transactionId = transactionId)
        }

        composable(route = Screen.About.route) {
            About()
        }
        composable(
            route = Screen.Transactions.route
        ) {
            Transactions(navHostController = navController)
        }
        composable(route=Screen.WalletScreen.route){
         Wallet()
        }
        composable(route=Screen.ProfileScreen.route){
            Profile()
        }

    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    BottomNavigation(
        backgroundColor = Color.White,
        elevation = 8.dp
    ) {
        // Dashboard Item
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = stringResource(com.example.xpense.R.string.dashboard),
                )
            },
            label = {
                Text(text = "Dashboard")
            },
            selectedContentColor = Color(0xFF243D25),
            unselectedContentColor = Color.Gray,
            alwaysShowLabel = false,
            selected = currentRoute == Screen.Dashboard.route,
            onClick = {
                navController.navigate(Screen.Dashboard.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

        // Wallet Item (Newly Added)
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Filled.AccountBalanceWallet,
                    contentDescription = stringResource(com.example.xpense.R.string.wallet),
                )
            },
            label = {
                Text(text = "Wallet")
            },
            selectedContentColor = Color(0xFF243D25),
            unselectedContentColor = Color.Gray,
            alwaysShowLabel = false,
            selected = currentRoute == Screen.WalletScreen.route, // Update the route if needed
            onClick = {
                navController.navigate(Screen.WalletScreen.route) { // Ensure to create a Wallet screen
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

        // Transactions Item
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Filled.Assignment,
                    contentDescription = stringResource(com.example.xpense.R.string.transactions),
                )
            },
            label = {
                Text(text = "Transactions")
            },
            selectedContentColor = Color(0xFF243D25),
            unselectedContentColor = Color.Gray,
            alwaysShowLabel = false,
            selected = currentRoute == Screen.Transactions.route,
            onClick = {
                navController.navigate(Screen.Transactions.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )

        // Profile Item (Newly Added at Last)
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = stringResource(com.example.xpense.R.string.profile),
                )
            },
            label = {
                Text(text = "Profile")
            },
            selectedContentColor = Color(0xFF243D25),
            unselectedContentColor = Color.Gray,
            alwaysShowLabel = false,
            selected = currentRoute == Screen.ProfileScreen.route, // Update the route if needed
            onClick = {
                navController.navigate(Screen.ProfileScreen.route) { // Ensure to create a Profile screen
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
        )
    }
}

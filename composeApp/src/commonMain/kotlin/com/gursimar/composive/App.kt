@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalAdaptiveApi::class)

package com.gursimar.composive

import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.gursimar.composive.examples.Material3ResponsiveLayoutExample
import com.slapps.cupertino.adaptive.ExperimentalAdaptiveApi


@Composable
fun App() {
    Material3ResponsiveLayoutExample()
}

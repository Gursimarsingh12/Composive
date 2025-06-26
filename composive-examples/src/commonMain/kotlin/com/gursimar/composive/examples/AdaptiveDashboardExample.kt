@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalAdaptiveApi::class)

package com.gursimar.composive.examples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Analytics
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import com.slapps.cupertino.adaptive.*
import com.slapps.cupertino.*
import com.gursimar.composive.responsive.core.rememberDeviceConfiguration
import com.gursimar.composive.responsive.core.DeviceConfiguration
import com.gursimar.composive.responsive.core.PlatformType
import com.gursimar.composive.responsive.configuration.responsiveConfiguration
import com.gursimar.composive.responsive.theme.ComposiveTheme
import com.gursimar.composive.responsive.theme.AppTheme
import com.gursimar.composive.responsive.foundation.smallFontWeights
import com.gursimar.composive.responsive.foundation.largeFontWeights
import com.slapps.cupertino.AlertActionStyle

data class DashboardCard(
    val title: String,
    val value: String,
    val subtitle: String,
    val icon: ImageVector,
    val color: Color
)

data class ActivityItem(
    val title: String,
    val description: String,
    val time: String,
    val icon: ImageVector
)

data class NavigationItem(
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

@Composable
fun AdaptiveDashboardExample() {
    ComposiveTheme(
        configuration = responsiveConfiguration {
            withCustomFontWeights(
                small = smallFontWeights.copy(heading = androidx.compose.ui.text.font.FontWeight.Bold),
                large = largeFontWeights.copy(display = androidx.compose.ui.text.font.FontWeight.Black)
            )
            withPlatformDefaultTheme()
        },
    ) {
        ResponsiveDashboardLayout()
    }
}

@Composable
private fun ResponsiveDashboardLayout() {
    val deviceConfig = rememberDeviceConfiguration()
    
    var selectedTab by remember { mutableIntStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
    var progressValue by remember { mutableFloatStateOf(0.7f) }
    var isChecked by remember { mutableStateOf(true) }
    var isSwitchOn by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    
    val dashboardCards = remember {
        listOf(
            DashboardCard("Total Sales", "$45,230", "+18% from last month", Icons.Default.TrendingUp, Color(0xFF4CAF50)),
            DashboardCard("Orders", "1,234", "+12% from last week", Icons.Default.ShoppingCart, Color(0xFF2196F3)),
            DashboardCard("Customers", "5,678", "+8% from yesterday", Icons.Default.People, Color(0xFF9C27B0)),
            DashboardCard("Revenue", "$98,765", "+25% from last month", Icons.Default.AttachMoney, Color(0xFFFF9800))
        )
    }
    
    val activities = remember {
        listOf(
            ActivityItem("Order completed", "Order #2156 has been delivered successfully", "5 min ago", Icons.Default.CheckCircle),
            ActivityItem("Payment received", "Payment of $540 processed successfully", "12 min ago", Icons.Default.Payment),
            ActivityItem("New review", "5-star review received on Product XYZ", "18 min ago", Icons.Default.Star),
            ActivityItem("System update", "Database optimization completed", "25 min ago", Icons.Default.Update),
            ActivityItem("New message", "Customer support ticket received", "35 min ago", Icons.Default.Message)
        )
    }
    
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        rememberTopAppBarState()
    )
    
    AdaptiveScaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            DashboardTopBar(
                deviceConfig = deviceConfig,
                scrollBehavior = scrollBehavior,
                onDialogClick = { showDialog = true },
                onBottomSheetClick = { showBottomSheet = true },
                onDatePickerClick = { showDatePicker = true },
                currentTab = selectedTab
            )
        },
        bottomBar = {
            DashboardNavigationBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it },
                deviceConfig = deviceConfig
            )
        }
    ) { paddingValues ->
        when (selectedTab) {
            0 -> {
                DashboardContent(
                    paddingValues = paddingValues,
                    deviceConfig = deviceConfig,
                    cards = dashboardCards,
                    activities = activities
                )
            }
            1 -> {
                AnalyticsContent(paddingValues = paddingValues, deviceConfig = deviceConfig)
            }
            2 -> {
                OrdersContent(paddingValues = paddingValues, deviceConfig = deviceConfig)
            }
            3 -> {
                SettingsContent(paddingValues = paddingValues, deviceConfig = deviceConfig)
            }
        }
    }
    
    if (showDialog) {
        ResponsiveDialog(
            deviceConfig = deviceConfig,
            onDismiss = { showDialog = false },
            progressValue = progressValue,
            isChecked = isChecked,
            onCheckedChange = { isChecked = it },
            isSwitchOn = isSwitchOn,
            onSwitchChange = { isSwitchOn = it },
            isLoading = isLoading,
            onLoadingToggle = { isLoading = !isLoading }
        )
    }
    
    if (showBottomSheet) {
        ResponsiveBottomSheet(
            deviceConfig = deviceConfig,
            bottomSheetState = bottomSheetState,
            onDismiss = { showBottomSheet = false },
            progressValue = progressValue,
            onProgressChange = { progressValue = it },
            isChecked = isChecked,
            onCheckedChange = { isChecked = it },
            isSwitchOn = isSwitchOn,
            onSwitchChange = { isSwitchOn = it }
        )
    }
    
    if (showDatePicker) {
        ResponsiveDatePicker(
            deviceConfig = deviceConfig,
            selectedDate = selectedDate,
            onDateSelected = { selectedDate = it },
            onDismiss = { showDatePicker = false }
        )
    }
}

@Composable
private fun DashboardTopBar(
    deviceConfig: DeviceConfiguration,
    scrollBehavior: TopAppBarScrollBehavior,
    onDialogClick: () -> Unit,
    onBottomSheetClick: () -> Unit,
    onDatePickerClick: () -> Unit,
    currentTab: Int = 0
) {
    val tabNames = listOf("Dashboard", "Analytics", "Orders", "Settings")
    
    AdaptiveTopAppBar(
        title = {
            Column(
                modifier = Modifier.padding(AppTheme.dimensions.screenPaddingHorizontal)
            ){
                Text(
                    text = tabNames.getOrElse(currentTab) { "Adaptive Dashboard" },
                    style = AppTheme.materialTypography.titleLarge,
                    fontWeight = AppTheme.fontWeights.heading
                )
                Text(
                    text = "${deviceConfig.name} Layout",
                    style = AppTheme.materialTypography.bodySmall,
                    fontWeight = AppTheme.fontWeights.caption
                )
            }
        },
        actions = {
            val maxActions = when (deviceConfig) {
                DeviceConfiguration.MOBILE_PORTRAIT -> 2
                DeviceConfiguration.MOBILE_LANDSCAPE -> 3
                DeviceConfiguration.TABLET_PORTRAIT -> 4
                DeviceConfiguration.TABLET_LANDSCAPE -> 5
                DeviceConfiguration.DESKTOP -> 6
            }
            
            if (maxActions >= 1) {
                IconButton(onClick = onDialogClick) {
                    Icon(
                        Icons.Default.Info,
                        contentDescription = "Show Dialog",
                        modifier = Modifier.size(AppTheme.dimensions.iconMedium)
                    )
                }
            }
            
            if (maxActions >= 2) {
                IconButton(onClick = onBottomSheetClick) {
                    Icon(
                        Icons.Default.Menu,
                        contentDescription = "Show Bottom Sheet",
                        modifier = Modifier.size(AppTheme.dimensions.iconMedium)
                    )
                }
            }
            
            if (maxActions >= 3) {
                IconButton(onClick = onDatePickerClick) {
                    Icon(
                        Icons.Default.DateRange,
                        contentDescription = "Date Picker",
                        modifier = Modifier.size(AppTheme.dimensions.iconMedium)
                    )
                }
            }
            
            if (maxActions >= 4) {
                IconButton(onClick = { }) {
                    Icon(
                        Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        modifier = Modifier.size(AppTheme.dimensions.iconMedium)
                    )
                }
            }
            
            if (maxActions >= 5) {
                IconButton(onClick = { }) {
                    Icon(
                        Icons.Default.Settings,
                        contentDescription = "Settings",
                        modifier = Modifier.size(AppTheme.dimensions.iconMedium)
                    )
                }
            }
            
            if (maxActions >= 6) {
                IconButton(onClick = { }) {
                    Icon(
                        Icons.Default.MoreVert,
                        contentDescription = "More Options",
                        modifier = Modifier.size(AppTheme.dimensions.iconMedium)
                    )
                }
            }
        }
    )
}

@Composable
private fun DashboardNavigationBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    deviceConfig: DeviceConfiguration
) {
    val maxTabs = when (deviceConfig) {
        DeviceConfiguration.MOBILE_PORTRAIT -> 4
        DeviceConfiguration.MOBILE_LANDSCAPE -> 4
        DeviceConfiguration.TABLET_PORTRAIT -> 4
        DeviceConfiguration.TABLET_LANDSCAPE -> 4
        DeviceConfiguration.DESKTOP -> 4
    }
    
    val navigationItems = listOf(
        NavigationItem("Dashboard", Icons.Default.Dashboard, Icons.Outlined.Dashboard),
        NavigationItem("Analytics", Icons.Default.Analytics, Icons.Outlined.Analytics),
        NavigationItem("Orders", Icons.Default.ShoppingCart, Icons.Outlined.ShoppingCart),
        NavigationItem("Settings", Icons.Default.Settings, Icons.Outlined.Settings)
    ).take(maxTabs)
    
    AdaptiveNavigationBar {
        navigationItems.forEachIndexed { index, item ->
            AdaptiveNavigationBarItem(
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                icon = {
                    Icon(
                        imageVector = if (selectedTab == index) item.selectedIcon else item.unselectedIcon,
                        contentDescription = item.label,
                        modifier = Modifier.size(AppTheme.dimensions.iconMedium)
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        style = AppTheme.materialTypography.labelMedium,
                        fontWeight = if (selectedTab == index) AppTheme.fontWeights.button else AppTheme.fontWeights.caption
                    )
                },
                alwaysShowLabel = when (deviceConfig) {
                    DeviceConfiguration.MOBILE_PORTRAIT -> false
                    else -> true
                },
                modifier = Modifier.padding(AppTheme.dimensions.space2)
            )
        }
    }
}

@Composable
private fun DashboardContent(
    paddingValues: PaddingValues,
    deviceConfig: DeviceConfiguration,
    cards: List<DashboardCard>,
    activities: List<ActivityItem>
) {
    when (deviceConfig) {
        DeviceConfiguration.MOBILE_PORTRAIT -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = AppTheme.dimensions.screenPaddingHorizontal),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.sectionSpacing)
            ) {
                item {
                    Text(
                        text = "Mobile Portrait (${deviceConfig.getRecommendedColumns()} Column)",
                        style = AppTheme.materialTypography.headlineSmall,
                        fontWeight = AppTheme.fontWeights.heading,
                        modifier = Modifier.padding(vertical = AppTheme.dimensions.space2)
                    )
                }
                
                items(cards) { card ->
                    DashboardCardItem(card = card, isCompact = true)
                }
                
                item {
                    QuickStatsCard(
                        title = "Quick Overview",
                        description = "Optimized for ${deviceConfig.name}"
                    )
                }
                
                item {
                    RecentActivitiesCard(
                        activities = activities.take(3),
                        isCompact = true
                    )
                }
            }
        }
        
        DeviceConfiguration.MOBILE_LANDSCAPE -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = AppTheme.dimensions.screenPaddingHorizontal),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.sectionSpacing)
            ) {
                item {
                    Text(
                        text = "Mobile Landscape (${deviceConfig.getRecommendedColumns()} Columns)",
                        style = AppTheme.materialTypography.headlineSmall,
                        fontWeight = AppTheme.fontWeights.heading,
                        modifier = Modifier.padding(vertical = AppTheme.dimensions.space2)
                    )
                }
                
                item {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(deviceConfig.getRecommendedColumns()),
                        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing),
                        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing),
                        modifier = Modifier.height(AppTheme.dimensions.imageLarge)
                    ) {
                        items(cards) { card ->
                            DashboardCardItem(card = card, isCompact = true)
                        }
                    }
                }
                
                item {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.itemSpacing)
                    ) {
                        QuickStatsCard(
                            title = "Landscape View",
                            description = "Optimized for ${deviceConfig.name}",
                            modifier = Modifier.weight(1f)
                        )
                        RecentActivitiesCard(
                            activities = activities.take(4),
                            isCompact = true,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
                
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = AppTheme.dimensions.cardElevation)
                    ) {
                        Column(
                            modifier = Modifier.padding(AppTheme.dimensions.cardPadding),
                            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space2)
                        ) {
                            Text(
                                text = "Mobile Landscape Features",
                                style = AppTheme.materialTypography.titleMedium,
                                fontWeight = AppTheme.fontWeights.heading
                            )
                            Text(
                                text = "Enhanced landscape mode with optimized horizontal layouts.",
                                style = AppTheme.materialTypography.bodyMedium,
                                fontWeight = AppTheme.fontWeights.body
                            )
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.itemSpacing)
                            ) {
                                items(5) { index ->
                                    Card(
                                        modifier = Modifier.size(
                                            width = AppTheme.dimensions.buttonHeightMedium * 1.5f,
                                            height = AppTheme.dimensions.buttonHeightMedium * 0.8f
                                        ),
                                        colors = CardDefaults.cardColors(
                                            containerColor = AppTheme.materialColors.tertiaryContainer
                                        )
                                    ) {
                                        Box(
                                            modifier = Modifier.fillMaxSize(),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = "${index + 1}",
                                                style = AppTheme.materialTypography.labelSmall,
                                                fontWeight = AppTheme.fontWeights.button
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        DeviceConfiguration.TABLET_PORTRAIT -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = AppTheme.dimensions.screenPaddingHorizontal),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.sectionSpacing)
            ) {
                item {
                    Text(
                        text = "Tablet Portrait (${deviceConfig.getRecommendedColumns()} Columns)",
                        style = AppTheme.materialTypography.headlineMedium,
                        fontWeight = AppTheme.fontWeights.heading,
                        modifier = Modifier.padding(vertical = AppTheme.dimensions.space3)
                    )
                }
                
                item {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(deviceConfig.getRecommendedColumns()),
                        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing),
                        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing),
                        modifier = Modifier.height(AppTheme.dimensions.imageLarge)
                    ) {
                        items(cards) { card ->
                            DashboardCardItem(card = card, isCompact = false)
                        }
                    }
                }
                
                item {
                    QuickStatsCard(
                        title = "Tablet Portrait View",
                        description = "Enhanced experience for ${deviceConfig.name} with ${deviceConfig.getRecommendedColumns()} column layout"
                    )
                }
                
                item {
                    RecentActivitiesCard(
                        activities = activities.take(5),
                        isCompact = false
                    )
                }
                
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = AppTheme.dimensions.cardElevation)
                    ) {
                        Column(
                            modifier = Modifier.padding(AppTheme.dimensions.cardPadding),
                            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space3)
                        ) {
                            Text(
                                text = "Tablet Features",
                                style = AppTheme.materialTypography.titleLarge,
                                fontWeight = AppTheme.fontWeights.heading
                            )
                            Text(
                                text = "Enhanced tablet experience with optimized layouts and additional features for better productivity.",
                                style = AppTheme.materialTypography.bodyMedium,
                                fontWeight = AppTheme.fontWeights.body
                            )
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.itemSpacing)
                            ) {
                                items(3) { index ->
                                    Card(
                                        modifier = Modifier.size(
                                            width = AppTheme.dimensions.buttonHeightMedium * 2,
                                            height = AppTheme.dimensions.buttonHeightMedium
                                        ),
                                        colors = CardDefaults.cardColors(
                                            containerColor = AppTheme.materialColors.secondaryContainer
                                        )
                                    ) {
                                        Box(
                                            modifier = Modifier.fillMaxSize(),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = "Feature ${index + 1}",
                                                style = AppTheme.materialTypography.labelMedium,
                                                fontWeight = AppTheme.fontWeights.button
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        DeviceConfiguration.TABLET_LANDSCAPE -> {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = AppTheme.dimensions.screenPaddingHorizontal),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.sectionSpacing)
            ) {
                LazyColumn(
                    modifier = Modifier.weight(2f),
                    verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.sectionSpacing)
                ) {
                    item {
                        Text(
                            text = "Tablet Landscape (${deviceConfig.getRecommendedColumns()} Columns)",
                            style = AppTheme.materialTypography.headlineMedium,
                            fontWeight = AppTheme.fontWeights.heading,
                            modifier = Modifier.padding(vertical = AppTheme.dimensions.space3)
                        )
                    }
                    
                    item {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(deviceConfig.getRecommendedColumns()),
                            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing),
                            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing),
                            modifier = Modifier.height(AppTheme.dimensions.imageMedium)
                        ) {
                            items(cards) { card ->
                                DashboardCardItem(card = card, isCompact = false)
                            }
                        }
                    }
                    
                    item {
                        QuickStatsCard(
                            title = "Tablet Landscape",
                            description = "Enhanced for ${deviceConfig.name} with responsive ${deviceConfig.getRecommendedColumns()}-column layout"
                        )
                    }
                    
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = AppTheme.dimensions.cardElevation)
                        ) {
                            Column(
                                modifier = Modifier.padding(AppTheme.dimensions.cardPadding),
                                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space3)
                            ) {
                                Text(
                                    text = "Extended Features",
                                    style = AppTheme.materialTypography.titleLarge,
                                    fontWeight = AppTheme.fontWeights.heading
                                )
                                Text(
                                    text = "This is additional scrollable content optimized for tablet landscape mode. The layout adapts to make full use of the available screen space.",
                                    style = AppTheme.materialTypography.bodyMedium,
                                    fontWeight = AppTheme.fontWeights.body
                                )
                                LazyRow(
                                    horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.itemSpacing)
                                ) {
                                    items(4) { index ->
                                        Card(
                                            modifier = Modifier.size(
                                                width = AppTheme.dimensions.buttonHeightMedium * 2,
                                                height = AppTheme.dimensions.buttonHeightMedium
                                            ),
                                            colors = CardDefaults.cardColors(
                                                containerColor = AppTheme.materialColors.primaryContainer
                                            )
                                        ) {
                                            Box(
                                                modifier = Modifier.fillMaxSize(),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text(
                                                    text = "Item ${index + 1}",
                                                    style = AppTheme.materialTypography.labelMedium,
                                                    fontWeight = AppTheme.fontWeights.button
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    item {
                        RecentActivitiesCard(
                            activities = activities,
                            isCompact = false
                        )
                    }
                }
            }
        }
        
        DeviceConfiguration.DESKTOP -> {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = AppTheme.dimensions.screenPaddingHorizontal),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.sectionSpacing)
            ) {
                LazyColumn(
                    modifier = Modifier.weight(3f),
                    verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.sectionSpacing)
                ) {
                    item {
                        Text(
                            text = "Desktop Layout (${deviceConfig.getRecommendedColumns()} Columns Available)",
                            style = AppTheme.materialTypography.headlineLarge,
                            fontWeight = AppTheme.fontWeights.display,
                            modifier = Modifier.padding(vertical = AppTheme.dimensions.space4)
                        )
                    }
                    
                    item {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(deviceConfig.getRecommendedColumns().coerceAtMost(2)),
                            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing),
                            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing),
                            modifier = Modifier.wrapContentHeight()
                        ) {
                            items(cards) { card ->
                                DashboardCardItem(card = card, isCompact = false)
                            }
                        }
                    }
                    
                    item {
                        QuickStatsCard(
                            title = "Desktop Experience",
                            description = "Comprehensive desktop experience with ${deviceConfig.getRecommendedColumns()}-column responsive layout for maximum productivity"
                        )
                    }
                    
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = AppTheme.dimensions.cardElevation)
                        ) {
                            Column(
                                modifier = Modifier.padding(AppTheme.dimensions.cardPadding),
                                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space3)
                            ) {
                                Text(
                                    text = "Desktop Analytics",
                                    style = AppTheme.materialTypography.titleLarge,
                                    fontWeight = AppTheme.fontWeights.heading
                                )
                                LazyRow(
                                    horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.itemSpacing)
                                ) {
                                    items(6) { index ->
                                        Card(
                                            modifier = Modifier.size(
                                                width = AppTheme.dimensions.buttonHeightMedium * 2.5f,
                                                height = AppTheme.dimensions.buttonHeightMedium * 1.5f
                                            ),
                                            colors = CardDefaults.cardColors(
                                                containerColor = cards[index % cards.size].color.copy(alpha = 0.1f)
                                            )
                                        ) {
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .padding(AppTheme.dimensions.space2),
                                                verticalArrangement = Arrangement.Center,
                                                horizontalAlignment = Alignment.CenterHorizontally
                                            ) {
                                                Icon(
                                                    imageVector = cards[index % cards.size].icon,
                                                    contentDescription = null,
                                                    tint = cards[index % cards.size].color,
                                                    modifier = Modifier.size(AppTheme.dimensions.iconMedium)
                                                )
                                                Text(
                                                    text = "Metric ${index + 1}",
                                                    style = AppTheme.materialTypography.labelSmall,
                                                    fontWeight = AppTheme.fontWeights.caption,
                                                    textAlign = TextAlign.Center
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = AppTheme.dimensions.cardElevation)
                        ) {
                            Column(
                                modifier = Modifier.padding(AppTheme.dimensions.cardPadding),
                                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space3)
                            ) {
                                Text(
                                    text = "Extended Desktop Features",
                                    style = AppTheme.materialTypography.titleLarge,
                                    fontWeight = AppTheme.fontWeights.heading
                                )
                                Text(
                                    text = "This desktop view provides comprehensive scrollable content with enhanced layouts and features specifically designed for larger screens. The content adapts responsively while maintaining optimal usability.",
                                    style = AppTheme.materialTypography.bodyMedium,
                                    fontWeight = AppTheme.fontWeights.body
                                )
                            }
                        }
                    }
                }
                
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    item {
                        RecentActivitiesCard(
                            activities = activities,
                            isCompact = false
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DashboardCardItem(
    card: DashboardCard,
    isCompact: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(
            if (isCompact) AppTheme.dimensions.buttonMinWidth
            else AppTheme.dimensions.buttonMinWidth + AppTheme.dimensions.space6
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = AppTheme.dimensions.cardElevation),
        colors = CardDefaults.cardColors(containerColor = card.color.copy(alpha = 0.1f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.dimensions.cardPadding),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = card.title,
                        style = if (isCompact) AppTheme.materialTypography.bodyMedium else AppTheme.materialTypography.bodyLarge,
                        fontWeight = AppTheme.fontWeights.body,
                        color = AppTheme.materialColors.onSurface.copy(alpha = 0.7f)
                    )
                    Text(
                        text = card.value,
                        style = if (isCompact) AppTheme.materialTypography.titleLarge else AppTheme.materialTypography.headlineSmall,
                        fontWeight = AppTheme.fontWeights.bold,
                        color = card.color
                    )
                }
                Icon(
                    imageVector = card.icon,
                    contentDescription = null,
                    tint = card.color,
                    modifier = Modifier.size(
                        if (isCompact) AppTheme.dimensions.iconSmall else AppTheme.dimensions.iconMedium
                    )
                )
            }
            
            if (!isCompact) {
                Text(
                    text = card.subtitle,
                    style = AppTheme.materialTypography.bodySmall,
                    fontWeight = AppTheme.fontWeights.caption,
                    color = AppTheme.materialColors.onSurface.copy(alpha = 0.6f)
                )
            }
        }
    }
}

@Composable
fun RecentActivitiesCard(
    activities: List<ActivityItem>,
    isCompact: Boolean,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = AppTheme.dimensions.cardElevation)
    ) {
        Column(
            modifier = Modifier.padding(AppTheme.dimensions.cardPadding)
        ) {
            Text(
                text = "Recent Activities",
                style = AppTheme.materialTypography.titleLarge,
                fontWeight = AppTheme.fontWeights.heading,
                modifier = Modifier.padding(bottom = AppTheme.dimensions.space4)
            )
            
            activities.forEach { activity ->
                ActivityRow(
                    activity = activity,
                    isCompact = isCompact
                )
                if (activity != activities.last()) {
                    AdaptiveHorizontalDivider(
                        modifier = Modifier.background(color = AppTheme.materialColors.outline.copy(alpha = 0.3f))
                    )
                }
            }
        }
    }
}

@Composable
private fun ActivityRow(
    activity: ActivityItem,
    isCompact: Boolean
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space3),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .size(AppTheme.dimensions.avatarSmall)
                .clip(RoundedCornerShape(AppTheme.dimensions.avatarSmall.div(2)))
                .background(AppTheme.materialColors.primary.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = activity.icon,
                contentDescription = null,
                tint = AppTheme.materialColors.primary,
                modifier = Modifier.size(AppTheme.dimensions.iconTiny)
            )
        }
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = activity.title,
                style = if (isCompact) AppTheme.materialTypography.bodyMedium else AppTheme.materialTypography.bodyLarge,
                fontWeight = AppTheme.fontWeights.body
            )
            if (!isCompact) {
                Text(
                    text = activity.description,
                    style = AppTheme.materialTypography.bodySmall,
                    fontWeight = AppTheme.fontWeights.caption,
                    color = AppTheme.materialColors.onSurface.copy(alpha = 0.7f),
                    modifier = Modifier.padding(top = AppTheme.dimensions.space1)
                )
            }
        }
        
        Text(
            text = activity.time,
            style = AppTheme.materialTypography.bodySmall,
            fontWeight = AppTheme.fontWeights.caption,
            color = AppTheme.materialColors.onSurface.copy(alpha = 0.5f)
        )
    }
}

@Composable
private fun ResponsiveDialog(
    deviceConfig: DeviceConfiguration,
    onDismiss: () -> Unit,
    progressValue: Float,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    isSwitchOn: Boolean,
    onSwitchChange: (Boolean) -> Unit,
    isLoading: Boolean,
    onLoadingToggle: () -> Unit
) {
    AdaptiveAlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Adaptive Components Demo",
                style = AppTheme.materialTypography.titleLarge,
                fontWeight = AppTheme.fontWeights.heading
            )
        },
        message = {
            Column(
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space4)
            ) {
                Text(
                    text = "Device: ${deviceConfig.name}",
                    style = AppTheme.materialTypography.bodyMedium,
                    fontWeight = AppTheme.fontWeights.body
                )
                
                AdaptiveHorizontalDivider(
                    modifier = Modifier.background(color = AppTheme.materialColors.outline.copy(alpha = 0.3f))
                )
                
                Column(
                    verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space2)
                ) {
                    Text(
                        text = "Progress Indicator",
                        style = AppTheme.materialTypography.titleSmall,
                        fontWeight = AppTheme.fontWeights.emphasis
                    )
                    LinearProgressIndicator(
                        progress = { progressValue },
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Text(
                        text = "${(progressValue * 100).toInt()}% Complete",
                        style = AppTheme.materialTypography.bodySmall,
                        fontWeight = AppTheme.fontWeights.caption
                    )
                }
                
                AdaptiveHorizontalDivider(
                    modifier = Modifier.background(color = AppTheme.materialColors.outline.copy(alpha = 0.3f))
                )
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Dark Mode",
                        style = AppTheme.materialTypography.bodyMedium,
                        fontWeight = AppTheme.fontWeights.body
                    )
                    AdaptiveSwitch(
                        checked = isSwitchOn,
                        onCheckedChange = onSwitchChange
                    )
                }
                
                if (isLoading) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space2),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AdaptiveCircularProgressIndicator(
                            modifier = Modifier.size(AppTheme.dimensions.iconSmall)
                        )
                        Text(
                            text = "Loading...",
                            style = AppTheme.materialTypography.bodyMedium,
                            fontWeight = AppTheme.fontWeights.body
                        )
                    }
                }
            }
        },
        buttons = {
            action(
                onClick = onLoadingToggle,
                style = AlertActionStyle.Default,
                enabled = true
            ) {
                Text(
                    text = if (isLoading) "Stop Loading" else "Start Loading",
                    style = AppTheme.materialTypography.labelLarge,
                    fontWeight = AppTheme.fontWeights.button
                )
            }
            action(
                onClick = onDismiss,
                style = AlertActionStyle.Cancel,
                enabled = true
            ) {
                Text(
                    text = "Close",
                    style = AppTheme.materialTypography.labelLarge,
                    fontWeight = AppTheme.fontWeights.button
                )
            }
        }
    )
}

@Composable
private fun ResponsiveBottomSheet(
    deviceConfig: DeviceConfiguration,
    bottomSheetState: SheetState,
    onDismiss: () -> Unit,
    progressValue: Float,
    onProgressChange: (Float) -> Unit,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    isSwitchOn: Boolean,
    onSwitchChange: (Boolean) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = bottomSheetState,
        modifier = Modifier.fillMaxHeight(
            when (deviceConfig) {
                DeviceConfiguration.MOBILE_PORTRAIT -> 0.9f
                DeviceConfiguration.MOBILE_LANDSCAPE -> 0.8f
                DeviceConfiguration.TABLET_PORTRAIT -> 0.7f
                DeviceConfiguration.TABLET_LANDSCAPE -> 0.6f
                DeviceConfiguration.DESKTOP -> 0.5f
            }
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimensions.cardPadding),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space4)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Bottom Sheet Demo",
                    style = AppTheme.materialTypography.headlineSmall,
                    fontWeight = AppTheme.fontWeights.heading
                )
                IconButton(onClick = onDismiss) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Close",
                        modifier = Modifier.size(AppTheme.dimensions.iconMedium)
                    )
                }
            }
            
            Text(
                text = "Layout: ${deviceConfig.name}",
                style = AppTheme.materialTypography.bodyLarge,
                fontWeight = AppTheme.fontWeights.body
            )
            
            AdaptiveHorizontalDivider(
                modifier = Modifier.background(color = AppTheme.materialColors.outline.copy(alpha = 0.3f))
            )
            
            Column(
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space2)
            ) {
                Text(
                    text = "Adjust Progress",
                    style = AppTheme.materialTypography.titleMedium,
                    fontWeight = AppTheme.fontWeights.emphasis
                )
                AdaptiveSlider(
                    value = progressValue,
                    onValueChange = onProgressChange,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Value: ${(progressValue * 100).toInt()}%",
                    style = AppTheme.materialTypography.bodyMedium,
                    fontWeight = AppTheme.fontWeights.caption,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            Spacer(modifier = Modifier.height(AppTheme.dimensions.space8))
        }
    }
}

@OptIn(ExperimentalCupertinoApi::class)
@Composable
private fun ResponsiveDatePicker(
    deviceConfig: DeviceConfiguration,
    selectedDate: Long?,
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = selectedDate?.let {
        rememberCupertinoDatePickerState(
        initialSelectedDateMillis = it.toLong()
    )
    }
    
    AdaptiveAlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Select Date",
                style = AppTheme.materialTypography.headlineSmall,
                fontWeight = AppTheme.fontWeights.heading
            )
        },
        message = {
            Column(
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space3)
            ) {
                Text(
                    text = "Device: ${deviceConfig.name}",
                    style = AppTheme.materialTypography.bodyMedium,
                    fontWeight = AppTheme.fontWeights.body
                )

                if (datePickerState != null) {
                    AdaptiveDatePicker(
                        state = datePickerState,
                        modifier = Modifier.height(AppTheme.dimensions.imageMedium)
                    )
                }
            }
        },
        buttons = {
            action(
                onClick = onDismiss,
                style = AlertActionStyle.Cancel,
                enabled = true
            ) {
                Text(
                    text = "Cancel",
                    style = AppTheme.materialTypography.labelLarge,
                    fontWeight = AppTheme.fontWeights.button
                )
            }
            action(
                onClick = {
                    val selectedEpochMillis = datePickerState?.selectedDateMillis
                    onDateSelected(selectedEpochMillis)
                    onDismiss()
                },
                style = AlertActionStyle.Default,
                enabled = true
            ) {
                Text(
                    text = "Select",
                    style = AppTheme.materialTypography.labelLarge,
                    fontWeight = AppTheme.fontWeights.button
                )
            }
        }
    )
}

@Composable
private fun AnalyticsContent(
    paddingValues: PaddingValues,
    deviceConfig: DeviceConfiguration
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = AppTheme.dimensions.screenPaddingHorizontal),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.sectionSpacing)
    ) {
        item {
            Text(
                text = "Analytics Dashboard",
                style = AppTheme.materialTypography.headlineMedium,
                fontWeight = AppTheme.fontWeights.heading,
                modifier = Modifier.padding(vertical = AppTheme.dimensions.space3)
            )
        }
        
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = AppTheme.dimensions.cardElevation)
            ) {
                Column(
                    modifier = Modifier.padding(AppTheme.dimensions.cardPadding),
                    verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space4)
                ) {
                    Text(
                        text = "Performance Metrics",
                        style = AppTheme.materialTypography.titleLarge,
                        fontWeight = AppTheme.fontWeights.heading
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        MetricItem("Conversion Rate", "12.5%", Icons.Default.TrendingUp)
                        MetricItem("Bounce Rate", "24.8%", Icons.Default.TrendingDown)
                        MetricItem("Page Views", "45.2K", Icons.Default.Visibility)
                    }
                    
                    Text(
                        text = "Device Configuration: ${deviceConfig.name}",
                        style = AppTheme.materialTypography.bodyMedium,
                        fontWeight = AppTheme.fontWeights.caption,
                        color = AppTheme.materialColors.onSurface.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}

@Composable
private fun OrdersContent(
    paddingValues: PaddingValues,
    deviceConfig: DeviceConfiguration
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = AppTheme.dimensions.screenPaddingHorizontal),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.sectionSpacing)
    ) {
        item {
            Text(
                text = "Orders Management",
                style = AppTheme.materialTypography.headlineMedium,
                fontWeight = AppTheme.fontWeights.heading,
                modifier = Modifier.padding(vertical = AppTheme.dimensions.space3)
            )
        }
        
        items(5) { index ->
            OrderItem(
                orderNumber = "#${2000 + index}",
                customerName = "Customer ${index + 1}",
                amount = "$${(100..500).random()}",
                status = listOf("Pending", "Processing", "Shipped", "Delivered").random(),
                deviceConfig = deviceConfig
            )
        }
    }
}

@Composable
private fun SettingsContent(
    paddingValues: PaddingValues,
    deviceConfig: DeviceConfiguration
) {
    var volumeValue by remember { mutableFloatStateOf(0.7f) }
    var temperatureRange by remember { mutableStateOf(18f..25f) }
    var brightnessRange by remember { mutableStateOf(0.2f..0.9f) }
    var darkModeEnabled by remember { mutableStateOf(false) }
    var notificationsEnabled by remember { mutableStateOf(true) }
    var autoSyncEnabled by remember { mutableStateOf(true) }
    
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = AppTheme.dimensions.screenPaddingHorizontal),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.sectionSpacing)
    ) {
        item {
            Text(
                text = "Settings",
                style = AppTheme.materialTypography.headlineMedium,
                fontWeight = AppTheme.fontWeights.heading,
                modifier = Modifier.padding(vertical = AppTheme.dimensions.space3)
            )
        }
        
        item {
            SettingsCard(
                title = "Device Information",
                deviceConfig = deviceConfig
            )
        }
        
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = AppTheme.dimensions.cardElevation)
            ) {
                Column(
                    modifier = Modifier.padding(AppTheme.dimensions.cardPadding),
                    verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space4)
                ) {
                    Text(
                        text = "Adaptive Controls Demo",
                        style = AppTheme.materialTypography.titleLarge,
                        fontWeight = AppTheme.fontWeights.heading
                    )
                    
                    Column(
                        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space2)
                    ) {
                        Text(
                            text = "Volume Level",
                            style = AppTheme.materialTypography.titleMedium,
                            fontWeight = AppTheme.fontWeights.emphasis
                        )
                        AdaptiveSlider(
                            value = volumeValue,
                            onValueChange = { volumeValue = it },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = "Current: ${(volumeValue * 100).toInt()}%",
                            style = AppTheme.materialTypography.bodySmall,
                            fontWeight = AppTheme.fontWeights.caption,
                            color = AppTheme.materialColors.onSurface.copy(alpha = 0.7f)
                        )
                    }
                    
                    AdaptiveHorizontalDivider(
                        modifier = Modifier.background(color = AppTheme.materialColors.outline.copy(alpha = 0.3f))
                    )
                    
                    Column(
                        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space2)
                    ) {
                        Text(
                            text = "Temperature Range",
                            style = AppTheme.materialTypography.titleMedium,
                            fontWeight = AppTheme.fontWeights.emphasis
                        )
                        AdaptiveRangeSlider(
                            value = temperatureRange,
                            onValueChange = { temperatureRange = it },
                            modifier = Modifier.fillMaxWidth(),
                            valueRange = 10f..35f
                        )
                        Text(
                            text = "Range: ${temperatureRange.start.toInt()}°C - ${temperatureRange.endInclusive.toInt()}°C",
                            style = AppTheme.materialTypography.bodySmall,
                            fontWeight = AppTheme.fontWeights.caption,
                            color = AppTheme.materialColors.onSurface.copy(alpha = 0.7f)
                        )
                    }
                    
                    AdaptiveHorizontalDivider(
                        modifier = Modifier.background(color = AppTheme.materialColors.outline.copy(alpha = 0.3f))
                    )
                    
                    Text(
                        text = "Adaptive Switches",
                        style = AppTheme.materialTypography.titleMedium,
                        fontWeight = AppTheme.fontWeights.emphasis
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Dark Mode",
                            style = AppTheme.materialTypography.bodyLarge,
                            fontWeight = AppTheme.fontWeights.body
                        )
                        AdaptiveSwitch(
                            checked = darkModeEnabled,
                            onCheckedChange = { darkModeEnabled = it }
                        )
                    }
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Push Notifications",
                            style = AppTheme.materialTypography.bodyLarge,
                            fontWeight = AppTheme.fontWeights.body
                        )
                        AdaptiveSwitch(
                            checked = notificationsEnabled,
                            onCheckedChange = { notificationsEnabled = it }
                        )
                    }
                    
                    Text(
                        text = "Platform: ${AppTheme.platform.name} (${if (AppTheme.platform.type == PlatformType.IOS) "Cupertino" else "Material"} Controls)",
                        style = AppTheme.materialTypography.bodySmall,
                        fontWeight = AppTheme.fontWeights.caption,
                        color = AppTheme.materialColors.primary.copy(alpha = 0.8f),
                        modifier = Modifier.padding(top = AppTheme.dimensions.space2)
                    )
                }
            }
        }
    }
}

@Composable
private fun MetricItem(
    title: String,
    value: String,
    icon: ImageVector
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space1)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = AppTheme.materialColors.primary,
            modifier = Modifier.size(AppTheme.dimensions.iconMedium)
        )
        Text(
            text = value,
            style = AppTheme.materialTypography.titleMedium,
            fontWeight = AppTheme.fontWeights.emphasis
        )
        Text(
            text = title,
            style = AppTheme.materialTypography.bodySmall,
            fontWeight = AppTheme.fontWeights.caption,
            color = AppTheme.materialColors.onSurface.copy(alpha = 0.7f)
        )
    }
}

@Composable
private fun OrderItem(
    orderNumber: String,
    customerName: String,
    amount: String,
    status: String,
    deviceConfig: DeviceConfiguration
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = AppTheme.dimensions.cardElevation)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimensions.cardPadding),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = orderNumber,
                    style = AppTheme.materialTypography.titleMedium,
                    fontWeight = AppTheme.fontWeights.heading
                )
                Text(
                    text = customerName,
                    style = AppTheme.materialTypography.bodyMedium,
                    fontWeight = AppTheme.fontWeights.body,
                    color = AppTheme.materialColors.onSurface.copy(alpha = 0.7f)
                )
            }
            
            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = amount,
                    style = AppTheme.materialTypography.titleMedium,
                    fontWeight = AppTheme.fontWeights.emphasis
                )
                Surface(
                    color = AppTheme.materialColors.primary.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(AppTheme.dimensions.space4)
                ) {
                    Text(
                        text = status,
                        style = AppTheme.materialTypography.labelSmall,
                        fontWeight = AppTheme.fontWeights.caption,
                        color = AppTheme.materialColors.primary,
                        modifier = Modifier.padding(
                            horizontal = AppTheme.dimensions.space2,
                            vertical = AppTheme.dimensions.space1
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun SettingsCard(
    title: String,
    deviceConfig: DeviceConfiguration
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = AppTheme.dimensions.cardElevation)
    ) {
        Column(
            modifier = Modifier.padding(AppTheme.dimensions.cardPadding),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space3)
        ) {
            Text(
                text = title,
                style = AppTheme.materialTypography.titleLarge,
                fontWeight = AppTheme.fontWeights.heading
            )
            
            SettingItem("Theme", "System Default")
            SettingItem("Language", "English")
            SettingItem("Device Layout", deviceConfig.name)
            SettingItem("Notifications", "Enabled")
        }
    }
}

@Composable
private fun SettingItem(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = AppTheme.materialTypography.bodyLarge,
            fontWeight = AppTheme.fontWeights.body
        )
        Text(
            text = value,
            style = AppTheme.materialTypography.bodyMedium,
            fontWeight = AppTheme.fontWeights.caption,
            color = AppTheme.materialColors.onSurface.copy(alpha = 0.7f)
        )
    }
}

@Composable
private fun QuickStatsCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(AppTheme.dimensions.imageMedium),
        elevation = CardDefaults.cardElevation(defaultElevation = AppTheme.dimensions.cardElevation)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(AppTheme.dimensions.cardPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    Icons.Default.BarChart,
                    contentDescription = null,
                    modifier = Modifier.size(AppTheme.dimensions.iconLarge),
                    tint = AppTheme.materialColors.primary.copy(alpha = 0.6f)
                )
                Spacer(modifier = Modifier.height(AppTheme.dimensions.space3))
                Text(
                    text = title,
                    style = AppTheme.materialTypography.titleLarge,
                    fontWeight = AppTheme.fontWeights.heading,
                    color = AppTheme.materialColors.onSurface.copy(alpha = 0.8f)
                )
                Text(
                    text = description,
                    style = AppTheme.materialTypography.bodyMedium,
                    fontWeight = AppTheme.fontWeights.body,
                    color = AppTheme.materialColors.onSurface.copy(alpha = 0.6f)
                )
                Text(
                    text = "Platform: ${AppTheme.platform.name}",
                    style = AppTheme.materialTypography.bodySmall,
                    fontWeight = AppTheme.fontWeights.caption,
                    color = AppTheme.materialColors.onSurface.copy(alpha = 0.4f)
                )
            }
        }
    }
} 
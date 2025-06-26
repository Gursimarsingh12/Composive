@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalAdaptiveApi::class)

package com.gursimar.composive.examples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import com.slapps.cupertino.adaptive.*
import com.slapps.cupertino.*
import com.gursimar.composive.responsive.core.rememberDeviceConfiguration
import com.gursimar.composive.responsive.core.DeviceConfiguration
import com.gursimar.composive.responsive.configuration.responsiveConfiguration
import com.gursimar.composive.responsive.theme.ComposiveTheme
import com.gursimar.composive.responsive.theme.AppTheme
import com.gursimar.composive.responsive.foundation.smallFontWeights
import com.gursimar.composive.responsive.foundation.largeFontWeights
import com.slapps.cupertino.AlertActionStyle

@Composable
fun AdaptiveComponentsShowcase() {
    ComposiveTheme(
        configuration = responsiveConfiguration {
            withCustomFontWeights(
                small = smallFontWeights.copy(heading = androidx.compose.ui.text.font.FontWeight.Bold),
                large = largeFontWeights.copy(display = androidx.compose.ui.text.font.FontWeight.Black)
            )
            withPlatformDefaultTheme()
        },
    ) {
        ComponentShowcaseLayout()
    }
}

@Composable
private fun ComponentShowcaseLayout() {
    val deviceConfig = rememberDeviceConfiguration()
    val scope = rememberCoroutineScope()
    
    var showDialog by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var progressValue by remember { mutableStateOf(0.7f) }
    var isChecked by remember { mutableStateOf(true) }
    var isSwitchOn by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )
    var showBottomSheet by remember { mutableStateOf(false) }
    
    val dashboardCards = remember {
        listOf(
            DashboardCard("Revenue", "$24,580", "+15% from last month", Icons.Default.TrendingUp, Color(0xFF4CAF50)),
            DashboardCard("Users", "4,290", "+8% from last week", Icons.Default.People, Color(0xFF2196F3)),
            DashboardCard("Orders", "2,156", "+12% from yesterday", Icons.Default.ShoppingCart, Color(0xFFFF9800)),
            DashboardCard("Growth", "32.4%", "+3.2% from last month", Icons.Default.Analytics, Color(0xFF9C27B0))
        )
    }
    
    val activities = remember {
        listOf(
            ActivityItem("New user registered", "Sarah Johnson joined the platform", "2 min ago", Icons.Default.PersonAdd),
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
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            ComponentShowcaseTopBar(
                deviceConfig = deviceConfig,
                scrollBehavior = scrollBehavior,
                onDialogClick = { showDialog = true },
                onBottomSheetClick = { showBottomSheet = true },
                onDatePickerClick = { showDatePicker = true },
            )
        }
    ) { paddingValues ->
        when (deviceConfig) {
            DeviceConfiguration.MOBILE_PORTRAIT -> MobilePortraitShowcase(
                paddingValues = paddingValues,
                cards = dashboardCards,
                activities = activities
            )
            DeviceConfiguration.MOBILE_LANDSCAPE -> MobileLandscapeShowcase(
                paddingValues = paddingValues,
                cards = dashboardCards,
                activities = activities
            )
            DeviceConfiguration.TABLET_PORTRAIT -> TabletPortraitShowcase(
                paddingValues = paddingValues,
                cards = dashboardCards,
                activities = activities
            )
            DeviceConfiguration.TABLET_LANDSCAPE -> TabletLandscapeShowcase(
                paddingValues = paddingValues,
                cards = dashboardCards,
                activities = activities
            )
            DeviceConfiguration.DESKTOP -> DesktopShowcase(
                paddingValues = paddingValues,
                cards = dashboardCards,
                activities = activities
            )
        }
    }
    
    if (showDialog) {
        ComponentDemoDialog(
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
        ComponentDemoBottomSheet(
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
        ComponentDemoDatePicker(
            deviceConfig = deviceConfig,
            selectedDate = selectedDate,
            onDateSelected = { selectedDate = it },
            onDismiss = { showDatePicker = false }
        )
    }
}

@Composable
private fun ComponentShowcaseTopBar(
    deviceConfig: DeviceConfiguration,
    scrollBehavior: TopAppBarScrollBehavior,
    onDialogClick: () -> Unit,
    onBottomSheetClick: () -> Unit,
    onDatePickerClick: () -> Unit
) {
    AdaptiveTopAppBar(
        title = {
            Column(
                modifier = Modifier.padding(AppTheme.dimensions.screenPaddingHorizontal)
            ){
                Text(
                    text = "Adaptive Components Showcase",
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
private fun MobilePortraitShowcase(
    paddingValues: PaddingValues,
    cards: List<DashboardCard>,
    activities: List<ActivityItem>
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
                text = "Mobile Portrait (${DeviceConfiguration.MOBILE_PORTRAIT.getRecommendedColumns()} Column)",
                style = AppTheme.materialTypography.headlineSmall,
                fontWeight = AppTheme.fontWeights.heading,
                modifier = Modifier.padding(vertical = AppTheme.dimensions.space2)
            )
        }
        
        item {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing),
                modifier = Modifier.height(AppTheme.dimensions.imageLarge * 2)
            ) {
                items(cards) { card ->
                    DashboardCardItem(
                        card = card,
                        isCompact = true
                    )
                }
            }
        }
        
        item {
            ComponentShowcaseCard(
                title = "Quick Overview",
                description = "Optimized for ${DeviceConfiguration.MOBILE_PORTRAIT.name}"
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

@Composable
private fun MobileLandscapeShowcase(
    paddingValues: PaddingValues,
    cards: List<DashboardCard>,
    activities: List<ActivityItem>
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
                text = "Mobile Landscape (${DeviceConfiguration.MOBILE_LANDSCAPE.getRecommendedColumns()} Columns)",
                style = AppTheme.materialTypography.headlineSmall,
                fontWeight = AppTheme.fontWeights.heading,
                modifier = Modifier.padding(vertical = AppTheme.dimensions.space2)
            )
        }
        
        item {
            LazyVerticalGrid(
                columns = GridCells.Fixed(DeviceConfiguration.MOBILE_LANDSCAPE.getRecommendedColumns()),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing),
                modifier = Modifier.height(AppTheme.dimensions.imageLarge)
            ) {
                items(cards) { card ->
                    DashboardCardItem(
                        card = card,
                        isCompact = true
                    )
                }
            }
        }
        
        item {
            Row(
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.itemSpacing)
            ) {
                ComponentShowcaseCard(
                    title = "Landscape View",
                    description = "Optimized for ${DeviceConfiguration.MOBILE_LANDSCAPE.name}",
                    modifier = Modifier.weight(1f)
                )
                RecentActivitiesCard(
                    activities = activities.take(4),
                    isCompact = true,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
private fun TabletPortraitShowcase(
    paddingValues: PaddingValues,
    cards: List<DashboardCard>,
    activities: List<ActivityItem>
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
                text = "Tablet Portrait (${DeviceConfiguration.TABLET_PORTRAIT.getRecommendedColumns()} Columns)",
                style = AppTheme.materialTypography.headlineMedium,
                fontWeight = AppTheme.fontWeights.heading,
                modifier = Modifier.padding(vertical = AppTheme.dimensions.space3)
            )
        }
        
        item {
            LazyVerticalGrid(
                columns = GridCells.Fixed(DeviceConfiguration.TABLET_PORTRAIT.getRecommendedColumns()),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing),
                modifier = Modifier.height(AppTheme.dimensions.imageLarge)
            ) {
                items(cards) { card ->
                    DashboardCardItem(
                        card = card,
                        isCompact = false
                    )
                }
            }
        }
        
        item {
            ComponentShowcaseCard(
                title = "Tablet Portrait View",
                description = "Enhanced experience for ${DeviceConfiguration.TABLET_PORTRAIT.name} with ${DeviceConfiguration.TABLET_PORTRAIT.getRecommendedColumns()} column layout"
            )
        }
        
        item {
            RecentActivitiesCard(
                activities = activities.take(5),
                isCompact = false
            )
        }
    }
}

@Composable
private fun TabletLandscapeShowcase(
    paddingValues: PaddingValues,
    cards: List<DashboardCard>,
    activities: List<ActivityItem>
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = AppTheme.dimensions.screenPaddingHorizontal),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.sectionSpacing)
    ) {
        Column(
            modifier = Modifier.weight(2f),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.sectionSpacing)
        ) {
            Text(
                text = "Tablet Landscape (${DeviceConfiguration.TABLET_LANDSCAPE.getRecommendedColumns()} Columns)",
                style = AppTheme.materialTypography.headlineMedium,
                fontWeight = AppTheme.fontWeights.heading,
                modifier = Modifier.padding(vertical = AppTheme.dimensions.space3)
            )
            
            LazyVerticalGrid(
                columns = GridCells.Fixed(DeviceConfiguration.TABLET_LANDSCAPE.getRecommendedColumns()),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing),
                modifier = Modifier.height(AppTheme.dimensions.imageMedium)
            ) {
                items(cards) { card ->
                    DashboardCardItem(
                        card = card,
                        isCompact = false
                    )
                }
            }
            
            ComponentShowcaseCard(
                title = "Tablet Landscape Dashboard",
                description = "Multi-column layout optimized for ${DeviceConfiguration.TABLET_LANDSCAPE.name}"
            )
        }
        
        Column(
            modifier = Modifier.weight(1f)
        ) {
            RecentActivitiesCard(
                activities = activities,
                isCompact = false
            )
        }
    }
}

@Composable
private fun DesktopShowcase(
    paddingValues: PaddingValues,
    cards: List<DashboardCard>,
    activities: List<ActivityItem>
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = AppTheme.dimensions.screenPaddingHorizontal),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.sectionSpacing)
    ) {
        Column(
            modifier = Modifier.weight(3f),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.sectionSpacing)
        ) {
            Text(
                text = "Desktop Layout (${DeviceConfiguration.DESKTOP.getRecommendedColumns()} Columns Available)",
                style = AppTheme.materialTypography.headlineLarge,
                fontWeight = AppTheme.fontWeights.display,
                modifier = Modifier.padding(vertical = AppTheme.dimensions.space4)
            )
            
            LazyVerticalGrid(
                columns = GridCells.Fixed(DeviceConfiguration.DESKTOP.getRecommendedColumns().coerceAtMost(2)),
                horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing),
                modifier = Modifier.wrapContentHeight()
            ) {
                items(cards) { card ->
                    DashboardCardItem(
                        card = card,
                        isCompact = false
                    )
                }
            }
            
            ComponentShowcaseCard(
                title = "Desktop Experience",
                description = "Full-featured showcase for ${DeviceConfiguration.DESKTOP.name} with maximum ${DeviceConfiguration.DESKTOP.getRecommendedColumns()}-column support"
            )
        }
        
        Column(
            modifier = Modifier.weight(1f)
        ) {
            RecentActivitiesCard(
                activities = activities,
                isCompact = false
            )
        }
    }
}

@Composable
private fun ComponentShowcaseCard(
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

@Composable
private fun ComponentDemoDialog(
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .toggleable(
                            value = isChecked,
                            onValueChange = onCheckedChange,
                            role = Role.Checkbox
                        )
                        .padding(AppTheme.dimensions.space2),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AdaptiveCheckbox(
                        checked = isChecked,
                        onCheckedChange = null
                    )
                    Spacer(modifier = Modifier.width(AppTheme.dimensions.space2))
                    Text(
                        text = "Enable notifications",
                        style = AppTheme.materialTypography.bodyMedium,
                        fontWeight = AppTheme.fontWeights.body
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
private fun ComponentDemoBottomSheet(
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
                    text = "Bottom Sheet Components Demo",
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
            
            AdaptiveHorizontalDivider(
                modifier = Modifier.background(color = AppTheme.materialColors.outline.copy(alpha = 0.3f))
            )
            
            val buttonCount = when (deviceConfig) {
                DeviceConfiguration.MOBILE_PORTRAIT -> 1
                DeviceConfiguration.MOBILE_LANDSCAPE -> 2
                DeviceConfiguration.TABLET_PORTRAIT -> 3
                DeviceConfiguration.TABLET_LANDSCAPE -> 4
                DeviceConfiguration.DESKTOP -> 4
            }
            
            if (buttonCount == 1) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space2)
                ) {
                    AdaptiveButton(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(AppTheme.dimensions.buttonHeightMedium)
                    ) {
                        Text(
                            text = "Primary Action",
                            fontWeight = AppTheme.fontWeights.button
                        )
                    }
                    
                    AdaptiveButton(
                        onClick = { },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(AppTheme.dimensions.buttonHeightMedium)
                    ) {
                        Text(
                            text = "Secondary Action",
                            fontWeight = AppTheme.fontWeights.button
                        )
                    }
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(minOf(buttonCount, 2)),
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space2),
                    verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space2),
                    modifier = Modifier.height(AppTheme.dimensions.buttonHeightLarge * 2)
                ) {
                    item {
                        AdaptiveButton(
                            onClick = { },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(AppTheme.dimensions.buttonHeightMedium)
                        ) {
                            Text(
                                text = "Primary",
                                fontWeight = AppTheme.fontWeights.button
                            )
                        }
                    }
                    
                    item {
                        AdaptiveButton(
                            onClick = { },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(AppTheme.dimensions.buttonHeightMedium)
                        ) {
                            Text(
                                text = "Outlined",
                                fontWeight = AppTheme.fontWeights.button
                            )
                        }
                    }
                    
                    if (buttonCount >= 3) {
                        item {
                            AdaptiveButton(
                                onClick = { },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(AppTheme.dimensions.buttonHeightMedium),
                                
                            ) {
                                Text(
                                    text = "Tonal",
                                    fontWeight = AppTheme.fontWeights.button
                                )
                            }
                        }
                    }
                    
                    if (buttonCount >= 4) {
                        item {
                            AdaptiveButton(
                                onClick = { },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(AppTheme.dimensions.buttonHeightMedium),
                            ) {
                                Text(
                                    text = "Text",
                                    fontWeight = AppTheme.fontWeights.button
                                )
                            }
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(AppTheme.dimensions.space8))
        }
    }
}

@OptIn(ExperimentalCupertinoApi::class)
@Composable
private fun ComponentDemoDatePicker(
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
                text = "Adaptive Date Picker",
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
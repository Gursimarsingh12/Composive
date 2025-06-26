package com.gursimar.composive.examples

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.horizontalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.gursimar.composive.responsive.configuration.materialResponsiveConfiguration
import com.gursimar.composive.responsive.core.DeviceConfiguration
import com.gursimar.composive.responsive.core.Orientation
import com.gursimar.composive.responsive.core.getMinTouchTargetSize
import com.gursimar.composive.responsive.core.getSpacingMultiplier
import com.gursimar.composive.responsive.core.rememberDeviceConfiguration
import com.gursimar.composive.responsive.core.shouldUseMaterial3
import com.gursimar.composive.responsive.theme.AppTheme
import com.gursimar.composive.responsive.theme.ComposiveTheme

data class ExampleMenuItem(
    val title: String,
    val icon: ImageVector,
    val description: String
)

data class ExampleNewsItem(
    val title: String,
    val description: String,
    val category: String
)

val sampleMenuItems = listOf(
    ExampleMenuItem("Dashboard", Icons.Default.Dashboard, "Overview of your data"),
    ExampleMenuItem("Analytics", Icons.Default.Analytics, "View detailed analytics"),
    ExampleMenuItem("Reports", Icons.Default.Assessment, "Generate custom reports"),
    ExampleMenuItem("Settings", Icons.Default.Settings, "Configure app settings"),
    ExampleMenuItem("Profile", Icons.Default.Person, "Manage your profile"),
    ExampleMenuItem("Help", Icons.Default.Help, "Get help and support")
)

val sampleNewsItems = listOf(
    ExampleNewsItem("Breaking News", "Important updates on the latest developments", "News"),
    ExampleNewsItem("Tech Innovation", "Latest advancements in technology", "Technology"),
    ExampleNewsItem("Market Analysis", "Current market trends and insights", "Business"),
    ExampleNewsItem("Health Tips", "Daily health and wellness advice", "Health"),
    ExampleNewsItem("Sports Update", "Latest sports news and results", "Sports"),
    ExampleNewsItem("Weather Alert", "Current weather conditions and forecasts", "Weather")
)

@Composable
fun Material3ResponsiveLayoutExample() {
    val config = materialResponsiveConfiguration()
    
    ComposiveTheme(configuration = config) {
        ResponsiveLayoutShowcase()
    }
}

@Composable
fun ResponsiveLayoutShowcase() {
    val deviceConfig = rememberDeviceConfiguration()
    val platform = AppTheme.platform
    
    val backgroundColor = if (platform.shouldUseMaterial3()) {
        MaterialTheme.colorScheme.background
    } else {
        MaterialTheme.colorScheme.surface
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        when (deviceConfig) {
            DeviceConfiguration.MOBILE_PORTRAIT -> {
                MobilePortraitLayoutExample()
            }
            DeviceConfiguration.MOBILE_LANDSCAPE -> {
                MobileLandscapeLayoutExample()
            }
            DeviceConfiguration.TABLET_PORTRAIT -> {
                TabletPortraitLayoutExample()
            }
            DeviceConfiguration.TABLET_LANDSCAPE -> {
                TabletLandscapeLayoutExample()
            }
            DeviceConfiguration.DESKTOP -> {
                DesktopLayoutExample()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MobilePortraitLayoutExample() {
    val dimensions = AppTheme.dimensions
    val platform = AppTheme.platform
    var selectedIndex by remember { mutableStateOf(0) }
    val scrollState = rememberScrollState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Material3 Responsive",
                        style = AppTheme.materialTypography.headlineSmall
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            NavigationBar {
                sampleMenuItems.take(4).forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index }
                    )
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(dimensions.screenPaddingHorizontal)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensions.space2),
                elevation = CardDefaults.cardElevation(defaultElevation = dimensions.cardElevation)
            ) {
                Column(
                    modifier = Modifier.padding(dimensions.cardPadding)
                ) {
                    Text(
                        "Mobile Portrait Layout Example",
                        style = AppTheme.materialTypography.headlineSmall,
                        fontWeight = AppTheme.fontWeights.heading
                    )
                    Spacer(modifier = Modifier.height(dimensions.space2))
                    Text(
                        "Optimized for mobile phones in portrait mode. Platform: ${platform.name}",
                        style = AppTheme.materialTypography.bodyMedium,
                        fontWeight = AppTheme.fontWeights.body
                    )
                }
            }
            
            DeviceConfigurationCard()
            
            Text(
                "Latest News",
                style = AppTheme.materialTypography.titleLarge,
                fontWeight = AppTheme.fontWeights.heading,
                modifier = Modifier.padding(vertical = dimensions.space3)
            )
            
            sampleNewsItems.forEach { item ->
                NewsItemCard(item, isCompact = true)
                Spacer(modifier = Modifier.height(dimensions.itemSpacing))
            }
            
            Spacer(modifier = Modifier.height(dimensions.space4))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MobileLandscapeLayoutExample() {
    val dimensions = AppTheme.dimensions
    val platform = AppTheme.platform
    val scrollState = rememberScrollState()
    val horizontalScrollState = rememberScrollState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Material3 Responsive - Mobile Landscape",
                        style = AppTheme.materialTypography.headlineSmall
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(dimensions.space2),
                modifier = Modifier
                    .horizontalScroll(horizontalScrollState)
                    .padding(horizontal = dimensions.screenPaddingHorizontal)
                    .padding(vertical = dimensions.space2)
            ) {
                sampleMenuItems.forEach { item ->
                    NavigationChipExample(item = item)
                }
            }
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensions.screenPaddingHorizontal)
            ) {
                Column(
                    modifier = Modifier.weight(2f)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = dimensions.space2),
                        elevation = CardDefaults.cardElevation(defaultElevation = dimensions.cardElevation)
                    ) {
                        Column(
                            modifier = Modifier.padding(dimensions.cardPadding)
                        ) {
                            Text(
                                "Mobile Landscape Layout Example",
                                style = AppTheme.materialTypography.headlineMedium,
                                fontWeight = AppTheme.fontWeights.heading
                            )
                            Spacer(modifier = Modifier.height(dimensions.space2))
                            Text(
                                "Optimized for mobile phones in landscape mode. Platform: ${platform.name}",
                                style = AppTheme.materialTypography.bodyLarge,
                                fontWeight = AppTheme.fontWeights.body
                            )
                        }
                    }
                    
                    Text(
                        "Latest Updates",
                        style = AppTheme.materialTypography.titleLarge,
                        fontWeight = AppTheme.fontWeights.heading,
                        modifier = Modifier.padding(vertical = dimensions.space3)
                    )
                    
                    Column(
                        verticalArrangement = Arrangement.spacedBy(dimensions.itemSpacing)
                    ) {
                        sampleNewsItems.chunked(2).forEach { rowItems ->
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(dimensions.itemSpacing),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                rowItems.forEach { item ->
                                    Box(modifier = Modifier.weight(1f)) {
                                        NewsItemCard(item, isCompact = true)
                                    }
                                }
                                if (rowItems.size == 1) {
                                    Spacer(modifier = Modifier.weight(1f))
                                }
                            }
                        }
                    }
                }
                
                Spacer(modifier = Modifier.width(dimensions.space4))
                
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(min = 200.dp, max = 400.dp)
                        .padding(vertical = dimensions.space2),
                    elevation = CardDefaults.cardElevation(defaultElevation = dimensions.cardElevation)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(dimensions.cardPadding)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            "Quick Info",
                            style = AppTheme.materialTypography.titleMedium,
                            fontWeight = AppTheme.fontWeights.heading
                        )
                        Spacer(modifier = Modifier.height(dimensions.space4))
                        
                        QuickInfoSection()
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(dimensions.space4))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabletPortraitLayoutExample() {
    val dimensions = AppTheme.dimensions
    val platform = AppTheme.platform
    var selectedIndex by remember { mutableStateOf(0) }
    val mainScrollState = rememberScrollState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Material3 Responsive - Tablet Portrait",
                        style = AppTheme.materialTypography.headlineMedium
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavigationRail(
                modifier = Modifier.padding(dimensions.space2)
            ) {
                Column(
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.height(dimensions.space4))
                    sampleMenuItems.forEachIndexed { index, item ->
                        NavigationRailItem(
                            icon = { Icon(item.icon, contentDescription = item.title) },
                            label = { Text(item.title) },
                            selected = selectedIndex == index,
                            onClick = { selectedIndex = index }
                        )
                    }
                }
            }
            
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(mainScrollState)
                    .padding(dimensions.screenPaddingHorizontal)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensions.space4),
                    elevation = CardDefaults.cardElevation(defaultElevation = dimensions.cardElevation * 2)
                ) {
                    Column(
                        modifier = Modifier.padding(dimensions.space5)
                    ) {
                        Text(
                            "Tablet Portrait Experience Example",
                            style = AppTheme.materialTypography.headlineLarge,
                            fontWeight = AppTheme.fontWeights.display
                        )
                        Spacer(modifier = Modifier.height(dimensions.space4))
                        Text(
                            "Designed for tablets in portrait mode with side navigation and spacious content areas. Platform: ${platform.name}",
                            style = AppTheme.materialTypography.bodyLarge,
                            fontWeight = AppTheme.fontWeights.body
                        )
                    }
                }
                
                Row {
                    Column(
                        modifier = Modifier.weight(2f)
                    ) {
                        Text(
                            "Featured Content",
                            style = AppTheme.materialTypography.titleLarge,
                            fontWeight = AppTheme.fontWeights.heading,
                            modifier = Modifier.padding(vertical = dimensions.space4)
                        )
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(dimensions.space4)
                        ) {
                            sampleNewsItems.chunked(2).forEach { rowItems ->
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(dimensions.space4),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    rowItems.forEach { item ->
                                        Box(modifier = Modifier.weight(1f)) {
                                            NewsItemCard(item, isCompact = false)
                                        }
                                    }
                                    if (rowItems.size == 1) {
                                        Spacer(modifier = Modifier.weight(1f))
                                    }
                                }
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.width(dimensions.space5))
                    
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .widthIn(max = 300.dp)
                    ) {
                        Text(
                            "Sidebar",
                            style = AppTheme.materialTypography.titleLarge,
                            fontWeight = AppTheme.fontWeights.heading,
                            modifier = Modifier.padding(vertical = dimensions.space4)
                        )
                        
                        DeviceConfigurationCard()
                        
                        Spacer(modifier = Modifier.height(dimensions.space4))
                        
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = dimensions.cardElevation)
                        ) {
                            Column(
                                modifier = Modifier.padding(dimensions.cardPadding)
                            ) {
                                QuickInfoSection()
                            }
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(dimensions.space6))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabletLandscapeLayoutExample() {
    val dimensions = AppTheme.dimensions
    val platform = AppTheme.platform
    var selectedIndex by remember { mutableStateOf(0) }
    val mainScrollState = rememberScrollState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Material3 Responsive - Tablet Landscape",
                        style = AppTheme.materialTypography.headlineLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Surface(
                modifier = Modifier.widthIn(min = 200.dp, max = 280.dp),
                tonalElevation = 4.dp
            ) {
                Column(
                    modifier = Modifier
                        .padding(dimensions.space4)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        "Navigation",
                        style = AppTheme.materialTypography.titleLarge,
                        fontWeight = AppTheme.fontWeights.heading,
                        modifier = Modifier.padding(bottom = dimensions.space4)
                    )
                    
                    sampleMenuItems.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            icon = { Icon(item.icon, contentDescription = item.title) },
                            label = { Text(item.title) },
                            selected = selectedIndex == index,
                            onClick = { selectedIndex = index },
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                    }
                }
            }
            
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(mainScrollState)
                    .padding(dimensions.space5)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = dimensions.space5),
                    elevation = CardDefaults.cardElevation(defaultElevation = dimensions.cardElevation * 2)
                ) {
                    Column(
                        modifier = Modifier.padding(dimensions.space5)
                    ) {
                        Text(
                            "Tablet Landscape Experience Example",
                            style = AppTheme.materialTypography.displayMedium,
                            fontWeight = AppTheme.fontWeights.display
                        )
                        Spacer(modifier = Modifier.height(dimensions.space4))
                        Text(
                            "Optimized for tablets in landscape mode with maximum content density. Platform: ${platform.name}",
                            style = AppTheme.materialTypography.bodyLarge,
                            fontWeight = AppTheme.fontWeights.body
                        )
                    }
                }
                
                Row {
                    Column(
                        modifier = Modifier.weight(2f)
                    ) {
                        Text(
                            "Featured Articles",
                            style = AppTheme.materialTypography.headlineMedium,
                            fontWeight = AppTheme.fontWeights.heading,
                            modifier = Modifier.padding(bottom = dimensions.space4)
                        )
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(dimensions.space4)
                        ) {
                            sampleNewsItems.chunked(3).forEach { rowItems ->
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(dimensions.space4),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    rowItems.forEach { item ->
                                        Box(modifier = Modifier.weight(1f)) {
                                            NewsItemCard(item, isCompact = false)
                                        }
                                    }
                                    repeat(3 - rowItems.size) {
                                        Spacer(modifier = Modifier.weight(1f))
                                    }
                                }
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.width(dimensions.space5))
                    
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .widthIn(max = 350.dp)
                    ) {
                        Text(
                            "Quick Actions",
                            style = AppTheme.materialTypography.headlineSmall,
                            fontWeight = AppTheme.fontWeights.heading,
                            modifier = Modifier.padding(bottom = dimensions.space4)
                        )
                        
                        DeviceConfigurationCard()
                        
                        Spacer(modifier = Modifier.height(dimensions.space5))
                        
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = dimensions.cardElevation)
                        ) {
                            Column(
                                modifier = Modifier.padding(dimensions.cardPadding)
                            ) {
                                Text(
                                    "Platform Info",
                                    style = AppTheme.materialTypography.titleMedium,
                                    fontWeight = AppTheme.fontWeights.heading
                                )
                                Spacer(modifier = Modifier.height(dimensions.space2))
                                
                                PlatformDetailsSection()
                            }
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(dimensions.space6))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DesktopLayoutExample() {
    val dimensions = AppTheme.dimensions
    val platform = AppTheme.platform
    var selectedIndex by remember { mutableStateOf(0) }
    val mainScrollState = rememberScrollState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Material3 Responsive - Desktop Experience",
                        style = AppTheme.materialTypography.headlineLarge
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Surface(
                modifier = Modifier.widthIn(min = 240.dp, max = 320.dp),
                tonalElevation = 4.dp
            ) {
                Column(
                    modifier = Modifier
                        .padding(dimensions.space4)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        "Navigation",
                        style = AppTheme.materialTypography.titleLarge,
                        fontWeight = AppTheme.fontWeights.heading,
                        modifier = Modifier.padding(bottom = dimensions.space4)
                    )
                    
                    sampleMenuItems.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            icon = { Icon(item.icon, contentDescription = item.title) },
                            label = { Text(item.title) },
                            selected = selectedIndex == index,
                            onClick = { selectedIndex = index },
                            modifier = Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }
            
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(mainScrollState)
                    .padding(dimensions.space6)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = dimensions.space6),
                    elevation = CardDefaults.cardElevation(defaultElevation = dimensions.cardElevation * 2)
                ) {
                    Column(
                        modifier = Modifier.padding(dimensions.space6)
                    ) {
                        Text(
                            "Desktop Experience Example",
                            style = AppTheme.materialTypography.displayLarge,
                            fontWeight = AppTheme.fontWeights.display
                        )
                        Spacer(modifier = Modifier.height(dimensions.space4))
                        Text(
                            "Optimized for desktop and large screens with maximum content density and rich interactive elements. Platform: ${platform.name}",
                            style = AppTheme.materialTypography.bodyLarge,
                            fontWeight = AppTheme.fontWeights.body
                        )
                    }
                }
                
                Row {
                    Column(
                        modifier = Modifier.weight(2f)
                    ) {
                        Text(
                            "Featured Articles",
                            style = AppTheme.materialTypography.headlineLarge,
                            fontWeight = AppTheme.fontWeights.display,
                            modifier = Modifier.padding(bottom = dimensions.space4)
                        )
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(dimensions.space4)
                        ) {
                            sampleNewsItems.chunked(3).forEach { rowItems ->
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(dimensions.space4),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    rowItems.forEach { item ->
                                        Box(modifier = Modifier.weight(1f)) {
                                            NewsItemCard(item, isCompact = false)
                                        }
                                    }
                                    repeat(3 - rowItems.size) {
                                        Spacer(modifier = Modifier.weight(1f))
                                    }
                                }
                            }
                        }
                    }
                    
                    Spacer(modifier = Modifier.width(dimensions.space6))
                    
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .widthIn(max = 400.dp)
                    ) {
                        Text(
                            "Quick Actions",
                            style = AppTheme.materialTypography.headlineMedium,
                            fontWeight = AppTheme.fontWeights.heading,
                            modifier = Modifier.padding(bottom = dimensions.space4)
                        )
                        
                        DeviceConfigurationCard()
                        
                        Spacer(modifier = Modifier.height(dimensions.space6))
                        
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = dimensions.cardElevation)
                        ) {
                            Column(
                                modifier = Modifier.padding(dimensions.cardPadding)
                            ) {
                                Text(
                                    "Platform Details",
                                    style = AppTheme.materialTypography.titleLarge,
                                    fontWeight = AppTheme.fontWeights.heading
                                )
                                Spacer(modifier = Modifier.height(dimensions.space3))
                                
                                PlatformDetailsSection()
                            }
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(dimensions.space8))
            }
        }
    }
}

@Composable
fun NewsItemCard(item: ExampleNewsItem, isCompact: Boolean) {
    val dimensions = AppTheme.dimensions
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = dimensions.cardElevation)
    ) {
        Column(
            modifier = Modifier.padding(
                if (isCompact) dimensions.space2 else dimensions.cardPadding
            )
        ) {
            Text(
                item.title,
                style = if (isCompact) AppTheme.materialTypography.titleSmall else AppTheme.materialTypography.titleMedium,
                fontWeight = AppTheme.fontWeights.heading
            )
            Spacer(modifier = Modifier.height(dimensions.space1))
            Text(
                item.description,
                style = if (isCompact) AppTheme.materialTypography.bodySmall else AppTheme.materialTypography.bodyMedium,
                fontWeight = AppTheme.fontWeights.body
            )
            Spacer(modifier = Modifier.height(dimensions.space2))
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Text(
                    item.category,
                    style = AppTheme.materialTypography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.padding(horizontal = dimensions.space2, vertical = dimensions.space1)
                )
            }
        }
    }
}

@Composable
fun NavigationChipExample(item: ExampleMenuItem) {
    val dimensions = AppTheme.dimensions
    
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = dimensions.space4, vertical = dimensions.space2)
        ) {
            Icon(
                item.icon,
                contentDescription = item.title,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.size(dimensions.iconSmall)
            )
            Spacer(modifier = Modifier.width(dimensions.space2))
            Text(
                item.title,
                style = AppTheme.materialTypography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun DeviceConfigurationCard() {
    val dimensions = AppTheme.dimensions
    val deviceConfig = rememberDeviceConfiguration()
    val orientation = AppTheme.orientation
    val platform = AppTheme.platform
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = dimensions.cardElevation)
    ) {
        Column(
            modifier = Modifier.padding(dimensions.cardPadding)
        ) {
            Text(
                "Device Configuration",
                style = AppTheme.materialTypography.titleMedium,
                fontWeight = AppTheme.fontWeights.heading
            )
            Spacer(modifier = Modifier.height(dimensions.space3))
            
            Column(
                verticalArrangement = Arrangement.spacedBy(dimensions.space2)
            ) {
                ConfigurationInfoItem("Configuration", deviceConfig.name)
                ConfigurationInfoItem("Orientation", orientation.toString())
                ConfigurationInfoItem("Platform", platform.name)
                ConfigurationInfoItem("Theme Preference", getThemeDisplayName(platform.shouldUseMaterial3()))
                ConfigurationInfoItem("Is Mobile", if (deviceConfig.isMobile()) "Yes" else "No")
                ConfigurationInfoItem("Is Tablet", if (deviceConfig.isTablet()) "Yes" else "No")
                ConfigurationInfoItem("Is Desktop", if (deviceConfig.isDesktop()) "Yes" else "No")
            }
        }
    }
}

@Composable
fun QuickInfoSection() {
    val dimensions = AppTheme.dimensions
    val deviceConfig = rememberDeviceConfiguration()
    val orientation = AppTheme.orientation
    val platform = AppTheme.platform
    
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensions.space2)
    ) {
        ConfigurationInfoItem("Device Type", when {
            deviceConfig.isMobile() -> "Mobile"
            deviceConfig.isTablet() -> "Tablet"
            deviceConfig.isDesktop() -> "Desktop"
            else -> "Unknown"
        })
        ConfigurationInfoItem("Orientation", if (orientation == Orientation.Portrait) "Portrait" else "Landscape")
        ConfigurationInfoItem("Platform Type", platform.type.name)
        ConfigurationInfoItem("Version", platform.name)
        ConfigurationInfoItem("Recommended Theme", if (platform.shouldUseMaterial3()) "Material 3" else "Cupertino")
    }
}

@Composable
fun ConfigurationInfoItem(label: String, value: String) {
    Column {
        Text(
            label,
            style = AppTheme.materialTypography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = AppTheme.fontWeights.caption
        )
        Text(
            value,
            style = AppTheme.materialTypography.bodyMedium,
            fontWeight = AppTheme.fontWeights.emphasis
        )
    }
}

@Composable
fun PlatformDetailsSection() {
    val dimensions = AppTheme.dimensions
    val platform = AppTheme.platform
    
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensions.space2)
    ) {
        ConfigurationInfoItem("Platform Type", platform.type.name)
        ConfigurationInfoItem("Version", platform.name)
        ConfigurationInfoItem("Preferred Theme", getThemeDisplayName(platform.shouldUseMaterial3()))
        ConfigurationInfoItem("Min Touch Target", "${platform.getMinTouchTargetSize()} dp")
        ConfigurationInfoItem("Spacing Multiplier", "${platform.getSpacingMultiplier()}x")
        ConfigurationInfoItem("Density Scale", "${platform.densityScale}x")
        ConfigurationInfoItem("Supports Dark Mode", if (platform.supportsDarkModeDetection) "Yes" else "No")
    }
}

fun getThemeDisplayName(shouldUseMaterial3: Boolean): String {
    return if (shouldUseMaterial3) "Material 3" else "Cupertino"
}
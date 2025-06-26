# Real-World Examples 🌟

Practical examples showing how to implement common UI patterns with Composive's responsive design system.

## 📱 Example 1: News Reader App

A complete news reader that adapts from mobile to desktop with different navigation patterns.

### App Structure

```kotlin
@Composable
fun NewsApp() {
    ComposiveTheme {
        val deviceConfig = rememberDeviceConfiguration()
        
        when (deviceConfig) {
            DeviceConfiguration.MOBILE_PORTRAIT -> {
                MobileNewsLayout()
            }
            DeviceConfiguration.MOBILE_LANDSCAPE,
            DeviceConfiguration.TABLET_PORTRAIT -> {
                TabletNewsLayout()
            }
            DeviceConfiguration.TABLET_LANDSCAPE,
            DeviceConfiguration.DESKTOP -> {
                DesktopNewsLayout()
            }
        }
    }
}
```

### Mobile Layout (Single Column)

```kotlin
@Composable
fun MobileNewsLayout() {
    var selectedCategory by remember { mutableStateOf("Technology") }
    
    Column(modifier = Modifier.fillMaxSize()) {
        // Top App Bar
        TopAppBar(
            title = { Text("News") },
            actions = {
                IconButton(onClick = { /* search */ }) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
            }
        )
        
        // Category Tabs
        ScrollableTabRow(
            selectedTabIndex = categories.indexOf(selectedCategory),
            modifier = Modifier.fillMaxWidth()
        ) {
            categories.forEach { category ->
                Tab(
                    selected = selectedCategory == category,
                    onClick = { selectedCategory = category },
                    text = { Text(category) }
                )
            }
        }
        
        // Articles List
        LazyColumn(
            contentPadding = PaddingValues(AppTheme.dimensions.screenPaddingHorizontal),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.itemSpacing),
            modifier = Modifier.weight(1f)
        ) {
            items(getArticles(selectedCategory)) { article ->
                MobileArticleCard(article)
            }
        }
        
        // Bottom Navigation
        NavigationBar {
            NavigationBarItem(
                icon = { Icon(Icons.Default.Home, null) },
                label = { Text("Home") },
                selected = true,
                onClick = { }
            )
            NavigationBarItem(
                icon = { Icon(Icons.Default.Bookmark, null) },
                label = { Text("Saved") },
                selected = false,
                onClick = { }
            )
        }
    }
}

@Composable
fun MobileArticleCard(article: Article) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(AppTheme.dimensions.cardElevation)
    ) {
        Column {
            AsyncImage(
                model = article.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(AppTheme.dimensions.imageMedium),
                contentScale = ContentScale.Crop
            )
            
            Column(
                modifier = Modifier.padding(AppTheme.dimensions.cardPadding)
            ) {
                Text(
                    text = article.title,
                    style = AppTheme.materialTypography.titleMedium,
                    fontWeight = AppTheme.fontWeights.heading,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(AppTheme.dimensions.space1))
                
                Text(
                    text = article.summary,
                    style = AppTheme.materialTypography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(AppTheme.dimensions.space2))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = article.source,
                        style = AppTheme.materialTypography.labelMedium,
                        color = LocalContentColor.current.copy(alpha = 0.7f)
                    )
                    Text(
                        text = article.timeAgo,
                        style = AppTheme.materialTypography.labelMedium,
                        color = LocalContentColor.current.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}
```

### Desktop Layout (Multi-Column with Drawer)

```kotlin
@Composable
fun DesktopNewsLayout() {
    var selectedCategory by remember { mutableStateOf("Technology") }
    var selectedArticle by remember { mutableStateOf<Article?>(null) }
    val drawerState = rememberDrawerState(DrawerValue.Open)
    
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(AppTheme.dimensions.dialogMaxWidth)
            ) {
                Column(
                    modifier = Modifier.padding(AppTheme.dimensions.contentPaddingLarge)
                ) {
                    Text(
                        text = "News Categories",
                        style = AppTheme.materialTypography.titleLarge,
                        fontWeight = AppTheme.fontWeights.heading
                    )
                    
                    Spacer(modifier = Modifier.height(AppTheme.dimensions.space4))
                    
                    categories.forEach { category ->
                        NavigationDrawerItem(
                            icon = { Icon(getCategoryIcon(category), null) },
                            label = { Text(category) },
                            selected = selectedCategory == category,
                            onClick = { 
                                selectedCategory = category
                                selectedArticle = null
                            },
                            modifier = Modifier.padding(
                                vertical = AppTheme.dimensions.space1
                            )
                        )
                    }
                }
            }
        }
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            // Articles List
            LazyColumn(
                contentPadding = PaddingValues(AppTheme.dimensions.screenPaddingHorizontal),
                verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing),
                modifier = Modifier.weight(1f)
            ) {
                item {
                    TopAppBar(
                        title = { Text(selectedCategory) },
                        navigationIcon = {
                            IconButton(
                                onClick = { /* toggle drawer */ }
                            ) {
                                Icon(Icons.Default.Menu, "Menu")
                            }
                        }
                    )
                }
                
                items(getArticles(selectedCategory)) { article ->
                    DesktopArticleCard(
                        article = article,
                        isSelected = selectedArticle == article,
                        onClick = { selectedArticle = article }
                    )
                }
            }
            
            // Article Detail Pane
            if (selectedArticle != null) {
                ArticleDetailPane(
                    article = selectedArticle!!,
                    onClose = { selectedArticle = null },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@Composable
fun DesktopArticleCard(
    article: Article,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surface
            }
        ),
        elevation = CardDefaults.cardElevation(AppTheme.dimensions.cardElevation)
    ) {
        Row(
            modifier = Modifier.padding(AppTheme.dimensions.cardPadding)
        ) {
            AsyncImage(
                model = article.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .size(AppTheme.dimensions.imageSmall)
                    .clip(RoundedCornerShape(AppTheme.dimensions.space2)),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(AppTheme.dimensions.space3))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = article.title,
                    style = AppTheme.materialTypography.titleMedium,
                    fontWeight = AppTheme.fontWeights.heading,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(AppTheme.dimensions.space1))
                
                Text(
                    text = article.summary,
                    style = AppTheme.materialTypography.bodyMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(AppTheme.dimensions.space2))
                
                Row {
                    Text(
                        text = article.source,
                        style = AppTheme.materialTypography.labelSmall,
                        color = LocalContentColor.current.copy(alpha = 0.7f)
                    )
                    Text(
                        text = " • ${article.timeAgo}",
                        style = AppTheme.materialTypography.labelSmall,
                        color = LocalContentColor.current.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}
```

## 🛍️ Example 2: E-commerce Product Grid

An adaptive product grid that shows different numbers of columns based on screen size.

```kotlin
@Composable
fun ProductGridScreen() {
    val deviceConfig = rememberDeviceConfiguration()
    
    val columns = deviceConfig.getRecommendedColumns()
    val showFilters = deviceConfig.isTablet() || deviceConfig.isDesktop()
    
    if (showFilters) {
        Row(modifier = Modifier.fillMaxSize()) {
            // Filters Sidebar
            FilterSidebar(
                modifier = Modifier.width(280.dp)
            )
            
            // Products Grid
            ProductGrid(
                columns = columns,
                modifier = Modifier.weight(1f)
            )
        }
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            // Mobile: Filters in bottom sheet or dialog
            TopAppBar(
                title = { Text("Products") },
                actions = {
                    IconButton(onClick = { /* show filters */ }) {
                        Icon(Icons.Default.FilterList, "Filters")
                    }
                }
            )
            
            ProductGrid(
                columns = columns,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ProductGrid(
    columns: Int,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        contentPadding = PaddingValues(AppTheme.dimensions.screenPaddingHorizontal),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing),
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.cardSpacing),
        modifier = modifier
    ) {
        items(products) { product ->
            ProductCard(product)
        }
    }
}

@Composable
fun ProductCard(product: Product) {
    val deviceConfig = rememberDeviceConfiguration()
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(AppTheme.dimensions.cardElevation)
    ) {
        Column {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            
            Column(
                modifier = Modifier.padding(AppTheme.dimensions.cardPadding)
            ) {
                Text(
                    text = product.name,
                    style = when (deviceConfig) {
                        DeviceConfiguration.MOBILE_PORTRAIT -> AppTheme.materialTypography.bodyMedium
                        else -> AppTheme.materialTypography.titleSmall
                    },
                    fontWeight = AppTheme.fontWeights.body,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(AppTheme.dimensions.space1))
                
                Text(
                    text = product.price,
                    style = AppTheme.materialTypography.titleMedium,
                    fontWeight = AppTheme.fontWeights.emphasis,
                    color = MaterialTheme.colorScheme.primary
                )
                
                if (deviceConfig.isTablet() || deviceConfig.isDesktop()) {
                    Spacer(modifier = Modifier.height(AppTheme.dimensions.space2))
                    
                    Button(
                        onClick = { /* add to cart */ },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Add to Cart")
                    }
                }
            }
        }
    }
}
```

## 📧 Example 3: Email Client

A master-detail email client that adapts its layout based on screen size.

```kotlin
@Composable
fun EmailClient() {
    var selectedEmail by remember { mutableStateOf<Email?>(null) }
    val deviceConfig = rememberDeviceConfiguration()
    
    when {
        deviceConfig.isMobile() -> {
            // Mobile: Stack navigation
            if (selectedEmail == null) {
                EmailList(
                    onEmailSelected = { selectedEmail = it }
                )
            } else {
                EmailDetail(
                    email = selectedEmail!!,
                    onBack = { selectedEmail = null }
                )
            }
        }
        
        deviceConfig.isTablet() -> {
            // Tablet: Side-by-side
            Row(modifier = Modifier.fillMaxSize()) {
                EmailList(
                    onEmailSelected = { selectedEmail = it },
                    modifier = Modifier.weight(1f)
                )
                
                if (selectedEmail != null) {
                    EmailDetail(
                        email = selectedEmail!!,
                        onBack = null, // No back button in tablet mode
                        modifier = Modifier.weight(1.5f)
                    )
                }
            }
        }
        
        else -> {
            // Desktop: Three-pane layout
            Row(modifier = Modifier.fillMaxSize()) {
                // Folders sidebar
                FolderSidebar(
                    modifier = Modifier.width(AppTheme.dimensions.dialogMaxWidth * 0.85f)
                )
                
                // Email list
                EmailList(
                    onEmailSelected = { selectedEmail = it },
                    modifier = Modifier.weight(1f)
                )
                
                // Email detail
                if (selectedEmail != null) {
                    EmailDetail(
                        email = selectedEmail!!,
                        onBack = null,
                        modifier = Modifier.weight(2f)
                    )
                }
            }
        }
    }
}
```

## 🎨 Example 4: Settings Screen

Adaptive settings screen that groups options differently based on screen size.

```kotlin
@Composable
fun SettingsScreen() {
    val deviceConfig = rememberDeviceConfiguration()
    
    if (deviceConfig.isMobile()) {
        // Mobile: Single column list
        LazyColumn(
            contentPadding = PaddingValues(AppTheme.dimensions.screenPaddingHorizontal),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space2)
        ) {
            settingsGroups.forEach { group ->
                item {
                    SettingsGroup(group)
                }
            }
        }
    } else {
        // Tablet/Desktop: Grid layout
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(AppTheme.dimensions.screenPaddingHorizontal),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space4),
            verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.space4)
        ) {
            items(settingsGroups) { group ->
                SettingsGroupCard(group)
            }
        }
    }
}

@Composable
fun SettingsGroup(group: SettingsGroup) {
    Column {
        Text(
            text = group.title,
            style = AppTheme.materialTypography.titleMedium,
            fontWeight = AppTheme.fontWeights.heading,
            modifier = Modifier.padding(
                horizontal = AppTheme.dimensions.space2,
                vertical = AppTheme.dimensions.space1
            )
        )
        
        Card {
            Column {
                group.items.forEachIndexed { index, item ->
                    SettingsItem(item)
                    
                    if (index < group.items.lastIndex) {
                        Divider()
                    }
                }
            }
        }
    }
}

@Composable
fun SettingsGroupCard(group: SettingsGroup) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(AppTheme.dimensions.cardElevation)
    ) {
        Column(
            modifier = Modifier.padding(AppTheme.dimensions.cardPadding)
        ) {
            Text(
                text = group.title,
                style = AppTheme.materialTypography.titleLarge,
                fontWeight = AppTheme.fontWeights.heading
            )
            
            Spacer(modifier = Modifier.height(AppTheme.dimensions.space3))
            
            group.items.forEach { item ->
                SettingsItemCompact(item)
                Spacer(modifier = Modifier.height(AppTheme.dimensions.space2))
            }
        }
    }
}
```

## 🔧 Example 5: Custom Theme Configuration

Real-world theme configuration for a branded app.

```kotlin
@Composable
fun BrandedApp() {
    ComposiveTheme(
        configuration = responsiveConfiguration {
            // Force Material 3 for brand consistency
            withMaterialTheme()
            
            // Brand fonts
            withMaterialReadingDisplayFonts(
                readingFont = interFontFamily,
                displayFont = poppinsFontFamily,
                labelFont = robotoFontFamily
            )
            
            // Brand colors
            withCustomMaterialColors(
                light = lightColorScheme(
                    primary = Color(0xFF1976D2),
                    secondary = Color(0xFF388E3C),
                    background = Color(0xFFFAFAFA),
                    surface = Color.White
                ),
                dark = darkColorScheme(
                    primary = Color(0xFF42A5F5),
                    secondary = Color(0xFF66BB6A),
                    background = Color(0xFF121212),
                    surface = Color(0xFF1E1E1E)
                )
            )
            
            // Custom dimensions for better mobile experience
            withCustomDimensions(
                small = smallDimensions.copy(
                    buttonHeightMedium = smallDimensions.buttonHeightMedium + AppTheme.dimensions.space1,
                    cardPadding = smallDimensions.cardPadding + AppTheme.dimensions.space2,
                    screenPaddingHorizontal = smallDimensions.screenPaddingHorizontal + AppTheme.dimensions.space2
                ),
                large = largeDimensions.copy(
                    buttonHeightMedium = largeDimensions.buttonHeightMedium + AppTheme.dimensions.space1,
                    cardPadding = largeDimensions.cardPadding + AppTheme.dimensions.space2,
                    screenPaddingHorizontal = largeDimensions.screenPaddingHorizontal + AppTheme.dimensions.space6
                )
            )
        }
    ) {
        MainApp()
    }
}

private val interFontFamily = FontFamily(
    Font(Res.font.inter_regular, FontWeight.Normal),
    Font(Res.font.inter_medium, FontWeight.Medium),
    Font(Res.font.inter_semibold, FontWeight.SemiBold),
    Font(Res.font.inter_bold, FontWeight.Bold)
)

private val poppinsFontFamily = FontFamily(
    Font(Res.font.poppins_regular, FontWeight.Normal),
    Font(Res.font.poppins_semibold, FontWeight.SemiBold),
    Font(Res.font.poppins_bold, FontWeight.Bold)
)
```

## 🧪 Testing & Hot Reload

Test all examples by running on desktop and resizing the window:

```bash
./gradlew desktopRunHot --mainClass com.example.myApp.MainKt --stacktrace --auto
```

Watch your layouts adapt in real-time as you resize from mobile to desktop sizes!

## 🎯 Key Takeaways

1. **Device Configuration** drives layout decisions
2. **AppTheme** provides consistent styling
3. **Responsive Configuration** enables customization
4. **Hot Reload** speeds up development
5. **Platform-aware defaults** ensure native feel

---

**Build amazing responsive apps with these proven patterns!** 🌟 
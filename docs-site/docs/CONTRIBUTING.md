# Contributing to Composive 🤝

Thank you for your interest in contributing to Composive! This guide will help you get started with contributing to the project.

## 🎯 Ways to Contribute

### 🐛 Bug Reports
- Found a bug? [Open an issue](https://github.com/Gursimarsingh12/Composive/issues)
- Include steps to reproduce, expected vs actual behavior
- Provide platform details (Android/iOS/Desktop)
- Include code samples when possible

### 📝 Documentation
- Fix typos, improve clarity, add examples
- Update API documentation
- Create tutorials and guides
- Translate documentation

### 🔧 Code Contributions
- Bug fixes and improvements
- New features (discuss first)
- Performance optimizations
- Platform-specific enhancements

## 🚀 Getting Started

### Prerequisites

- **Kotlin/JVM**: 1.9.0+
- **Android Studio**: Latest stable
- **Xcode**: Latest stable (for iOS development)
- **Git**: Latest version

### Fork and Clone

1. **Fork** the repository on GitHub
2. **Clone** your fork locally:
```bash
git clone https://github.com/YOUR_USERNAME/Composive.git
cd Composive
```

3. **Add upstream** remote:
```bash
git remote add upstream https://github.com/Gursimarsingh12/Composive.git
```

### Project Structure

```
Composive/
├── composive-responsive-adaptive/
│   ├── src/
│   │   ├── commonMain/kotlin/com/gursimar/composive/responsive/
│   │   │   ├── core/              # Device detection & platform APIs
│   │   │   ├── theme/             # Theme system & AppTheme
│   │   │   ├── foundation/        # Typography, dimensions, font weights
│   │   │   └── configuration/     # Configuration builders & data classes
│   │   ├── androidMain/kotlin/    # Android-specific implementations
│   │   ├── iosMain/kotlin/        # iOS-specific implementations
│   │   └── desktopMain/kotlin/    # Desktop-specific implementations
│   └── build.gradle.kts
├── composive-examples/            # Example module
├── composeApp/                    # Sample application
├── docs/                          # Documentation
├── settings.gradle.kts
└── build.gradle.kts
```

### Building the Project

```bash
# Build all targets
./gradlew build

# Run tests
./gradlew test

# Run sample app (desktop)
./gradlew :composeApp:desktopRun

# Run sample app (Android)
./gradlew :composeApp:installDebug
```

## 📋 Development Guidelines

### Code Style

- **Kotlin Coding Conventions**: Follow [official Kotlin style guide](https://kotlinlang.org/docs/coding-conventions.html)
- **4-space indentation** for Kotlin code
- **120 character line limit**
- **Use meaningful names** for variables, functions, and classes

#### Example:
```kotlin
@Composable
fun ResponsiveCard(
    title: String,
    description: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = AppTheme.dimensions.cardElevation
        )
    ) {
        Column(
            modifier = Modifier.padding(AppTheme.dimensions.cardPadding)
        ) {
            Text(
                text = title,
                style = AppTheme.materialTypography.titleMedium,
                fontWeight = AppTheme.fontWeights.heading
            )
            
            Text(
                text = description,
                style = AppTheme.materialTypography.bodyMedium
            )
        }
    }
}
```

### Documentation Standards

#### Public APIs
All public APIs must have KDoc documentation:

```kotlin
/**
 * Remember the current device configuration based on window size class.
 * 
 * This is the primary function you'll use to get responsive information in your composables.
 * It automatically calculates the appropriate device configuration based on the current
 * window size and provides intelligent defaults for layout decisions.
 * 
 * @return The current DeviceConfiguration
 * 
 * @sample
 * ```kotlin
 * @Composable
 * fun AdaptiveLayout() {
 *     val deviceConfig = rememberDeviceConfiguration()
 *     
 *     when (deviceConfig) {
 *         DeviceConfiguration.MOBILE_PORTRAIT -> SingleColumnLayout()
 *         DeviceConfiguration.DESKTOP -> MultiColumnLayout()
 *         else -> TwoColumnLayout()
 *     }
 * }
 * ```
 */
@Composable
fun rememberDeviceConfiguration(): DeviceConfiguration
```

#### Internal APIs
Internal APIs should have basic documentation:

```kotlin
/**
 * Internal function that maps WindowSizeClass values to ResponsiveSize values.
 */
internal fun mapToResponsiveSize(sizeClass: Any): ResponsiveSize
```

### Testing

#### Unit Tests
- Test all public APIs
- Test edge cases and error conditions
- Mock external dependencies

```kotlin
class DeviceConfigurationTest {
    @Test
    fun `mobile portrait detection works correctly`() {
        val windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(400.dp, 800.dp))
        val config = DeviceConfiguration.fromWindowSizeClass(windowSizeClass)
        
        assertEquals(DeviceConfiguration.MOBILE_PORTRAIT, config)
        assertTrue(config.isMobile())
        assertTrue(config.isPortrait())
    }
}
```

#### Integration Tests
- Test theme integration
- Test responsive behavior
- Test platform-specific implementations

### Platform Considerations

#### Android
- Follow Material Design guidelines
- Support API 21+
- Test on different screen densities

#### iOS
- Follow Human Interface Guidelines  
- Support iOS 13.0+
- Test on different device sizes

#### Desktop
- Support Windows, macOS, Linux
- Test window resizing behavior
- Consider mouse/keyboard interactions

## 🔧 Making Changes

### 1. Create a Branch

```bash
git checkout -b feature/my-awesome-feature
# or
git checkout -b fix/bug-description
```

### 2. Make Your Changes

- Write clean, well-documented code
- Add tests for new functionality
- Update documentation as needed
- Follow existing patterns and conventions

### 3. Test Your Changes

```bash
# Run all tests
./gradlew test

# Test on all platforms
./gradlew :composeApp:desktopRun
./gradlew :composeApp:installDebug
# Test iOS in Xcode

# Check code style
./gradlew ktlintCheck
```

### 4. Commit Your Changes

Use [Conventional Commits](https://www.conventionalcommits.org/):

```bash
git commit -m "feat: add responsive avatar component"
git commit -m "fix: device configuration on landscape tablets"
git commit -m "docs: improve getting started guide"
```

Types:
- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation changes
- `style`: Code style changes
- `refactor`: Code refactoring
- `test`: Adding tests
- `chore`: Build/tooling changes

### 5. Push and Create PR

```bash
git push origin feature/my-awesome-feature
```

Create a Pull Request on GitHub with:
- **Clear title** describing the change
- **Detailed description** of what and why
- **Screenshots** for UI changes
- **Breaking changes** if any
- **Testing notes** for reviewers

## 📝 Documentation Contributions

### Adding Examples

Add practical examples to `docs/examples.md`:

```kotlin
## 📧 Example: Email Client

A responsive email client that adapts its layout based on screen size.

```kotlin
@Composable
fun EmailClient() {
    val deviceConfig = rememberDeviceConfiguration()
    // ... implementation
}
```

### Improving API Documentation

Update API docs in `docs/api-reference.md`:

```markdown
### newFunction

Description of the function and its purpose.

```kotlin
@Composable
fun newFunction(parameter: String): ReturnType
```

**Parameters:**
- `parameter` - Description of parameter

**Returns:** Description of return value

**Example:**
```kotlin
val result = newFunction("example")
```
```

## 🧪 Testing Changes

### Manual Testing

1. **Run sample apps** on all platforms
2. **Test responsive behavior** by resizing windows
3. **Verify platform themes** work correctly
4. **Check hot reload** functionality

### Automated Testing

```bash
# Run unit tests
./gradlew test

# Run lint checks
./gradlew ktlintCheck

# Build all platforms
./gradlew build
```

### Hot Reload Testing

```bash
./gradlew :composeApp:desktopRunHot --auto
```

Test by resizing the window to different sizes and verifying:
- Mobile portrait (< 600dp width)
- Mobile landscape (600-840dp width, short height)
- Tablet portrait (600-840dp width, tall height)
- Tablet landscape (840-1200dp width)
- Desktop (> 1200dp width)

## 🚫 What Not to Include

### Breaking Changes
Avoid breaking changes unless absolutely necessary. If required:
- Discuss in an issue first
- Provide migration guide
- Deprecate old APIs gradually

### Large Refactors
Discuss major refactoring in an issue before starting work.

### Unrelated Changes
Keep PRs focused. Don't mix feature additions with code style changes.

### Dependencies
Don't add new dependencies without discussion. Composive aims to be lightweight.

## 🎯 Review Process

### What We Look For

1. **Functionality** - Does it work as intended?
2. **Code Quality** - Is it clean and maintainable?
3. **Documentation** - Are APIs documented?
4. **Tests** - Are there appropriate tests?
5. **Platform Support** - Works across platforms?

### Review Timeline

- **Initial review** within 48 hours
- **Feedback incorporation** depends on complexity
- **Merge** after approval and CI passes

## 🏆 Recognition

Contributors are recognized in:
- **CREDITS.md** - All contributors listed
- **Release notes** - Major contributions highlighted
- **README.md** - Top contributors featured

## ❓ Getting Help

### Questions
- **[Issues](https://github.com/Gursimarsingh12/Composive/issues)** - Bug reports and feature requests

### Direct Contact
- **Email** - anonymouslike083@gmail.com (for urgent issues)
- **LinkedIn** - [Gursimar Singh](https://www.linkedin.com/in/gursimar-singh-ba4196204/)

## 📜 Code of Conduct

Be respectful, inclusive, and constructive in all interactions. We follow the [Contributor Covenant](https://www.contributor-covenant.org/) code of conduct.

---

**Thank you for contributing to Composive!** 🎉  
*Together, we're making responsive design accessible to all developers.* 
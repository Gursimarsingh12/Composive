# Composive Documentation Site

This folder contains the documentation site for Composive, built with [Material for MkDocs](https://squidfunk.github.io/mkdocs-material/).

## 🚀 Quick Start

### Prerequisites

- Python 3.8 or higher
- pip (Python package installer)

### Setup

1. **Install dependencies:**
   ```bash
   pip install -r requirements.txt
   ```

2. **Serve locally:**
   ```bash
   mkdocs serve
   ```

3. **Open in browser:**
   ```
   http://localhost:8000
   ```

## 📁 Folder Structure

```
docs-site/
├── mkdocs.yml              # MkDocs configuration
├── requirements.txt        # Python dependencies
├── README.md              # This file
├── docs/                  # Documentation content
│   ├── index.md           # Homepage
│   ├── overview.md        # Overview page
│   ├── getting-started.md # Quick start guide
│   ├── installation.md    # Installation guide
│   ├── examples.md        # Usage examples  
│   ├── api-reference.md   # API documentation
│   ├── stylesheets/       # Custom CSS
│   │   └── extra.css      # Additional styling
│   ├── javascript/        # Custom JS
│   │   └── extra.js       # Interactive features
│   └── assets/            # Images and media
└── site/                  # Generated site (created by mkdocs build)
```

## 🛠 Development

### Local Development

```bash
# Install dependencies
pip install -r requirements.txt

# Start development server with live reload
mkdocs serve

# Build the site
mkdocs build

# Clean build artifacts
rm -rf site/
```

### Making Changes

1. Edit markdown files in the `docs/` folder
2. Modify `mkdocs.yml` for site configuration
3. Add custom styles in `docs/stylesheets/extra.css`
4. Add interactive features in `docs/javascript/extra.js`

### Adding New Pages

1. Create a new `.md` file in the `docs/` folder
2. Add it to the navigation in `mkdocs.yml`:
   ```yaml
   nav:
     - Home: index.md
     - Your New Page: your-new-page.md
   ```

## 🎨 Customization

### Theme Colors

The site uses a purple theme matching Composive's branding. To customize:

1. Edit `mkdocs.yml`:
   ```yaml
   theme:
     palette:
       - scheme: default
         primary: your-color
         accent: your-accent-color
   ```

2. Update CSS variables in `docs/stylesheets/extra.css`:
   ```css
   :root {
     --md-primary-fg-color: #your-color;
     --md-accent-fg-color: #your-accent-color;
   }
   ```

### Custom Components

Add custom HTML/CSS components:

1. Create HTML in markdown files using `md_in_html` extension
2. Style with custom CSS in `extra.css`
3. Add interactivity with JavaScript in `extra.js`

## 🚀 Deployment

### GitHub Pages

1. **Build the site:**
   ```bash
   mkdocs build
   ```

2. **Deploy to GitHub Pages:**
   ```bash
   mkdocs gh-deploy
   ```

### Manual Deployment

1. **Build the site:**
   ```bash
   mkdocs build
   ```

2. **Upload `site/` folder** to your web server

### Continuous Deployment

Create `.github/workflows/docs.yml`:

```yaml
name: Deploy Documentation
on:
  push:
    branches: [ main ]
    paths: [ 'docs-site/**' ]

jobs:
  deploy:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - uses: actions/setup-python@v4
        with:
          python-version: 3.x
      - run: pip install -r docs-site/requirements.txt
      - run: mkdocs gh-deploy --config-file docs-site/mkdocs.yml --remote-branch gh-pages
```

## 📝 Writing Guidelines

### Markdown Best Practices

- Use descriptive headings
- Include code examples with syntax highlighting
- Add admonitions for tips, warnings, and notes
- Use tables for structured data
- Include links to related sections

### Code Examples

Use fenced code blocks with language specification:

```kotlin
@Composable
fun MyComponent() {
    ComposiveTheme {
        Text("Hello Composive!")
    }
}
```

### Admonitions

Use Material for MkDocs admonitions:

```markdown
!!! tip "Pro Tip"
    This is a helpful tip for users.

!!! warning "Important"
    This is important information to remember.

!!! example "Example"
    Here's how to implement this feature.
```

## 🔧 Troubleshooting

### Common Issues

1. **Python dependencies not installing:**
   ```bash
   pip install --upgrade pip
   pip install -r requirements.txt
   ```

2. **Site not updating:**
   - Clear browser cache
   - Stop and restart `mkdocs serve`
   - Check for syntax errors in markdown

3. **Deployment failing:**
   - Verify GitHub Pages is enabled
   - Check repository permissions
   - Ensure `gh-pages` branch exists

### Getting Help

- [Material for MkDocs Documentation](https://squidfunk.github.io/mkdocs-material/)
- [MkDocs Documentation](https://www.mkdocs.org/)
- [Python Markdown Extensions](https://python-markdown.github.io/extensions/)

## 📄 License

This documentation site is part of the Composive project and is licensed under the Apache License 2.0. 
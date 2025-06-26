// Enhanced SEO Script for Composive Documentation
// Comprehensive structured data and advanced SEO features

function addStructuredData() {
    const structuredData = {
        "@context": "https://schema.org",
        "@graph": [
            {
                "@type": "SoftwareApplication",
                "name": "Composive",
                "description": "Responsive and adaptive UI library for Compose Multiplatform applications",
                "url": "https://gursimarsingh12.github.io/Composive/",
                "author": {
                    "@type": "Person",
                    "name": "Gursimar Singh",
                    "email": "anonymouslike083@gmail.com",
                    "url": "https://github.com/Gursimarsingh12"
                },
                "programmingLanguage": "Kotlin",
                "runtimePlatform": ["Android", "iOS", "Desktop", "Web"],
                "applicationCategory": "DeveloperApplication",
                "operatingSystem": ["Android", "iOS", "Windows", "macOS", "Linux"],
                "downloadUrl": "https://github.com/Gursimarsingh12/Composive",
                "codeRepository": "https://github.com/Gursimarsingh12/Composive",
                "license": "https://github.com/Gursimarsingh12/Composive/blob/main/LICENSE",
                "keywords": [
                    "Composive", "Compose Multiplatform", "Responsive Design", "Adaptive UI",
                    "Kotlin", "Android", "iOS", "Desktop", "Cross Platform", "UI Library",
                    "kotlin-library", "compose", "adaptive", "responsive-design",
                    "kotlin-multiplatform", "jetpack-compose", "compose-ui", "compose-desktop",
                    "compose-multiplatform", "compose-multiplatform-library", "mobile-ui",
                    "cross-platform-ui", "ui-framework", "responsive-components", "adaptive-design"
                ],
                "offers": {
                    "@type": "Offer",
                    "price": "0.00",
                    "priceCurrency": "USD",
                    "availability": "https://schema.org/InStock"
                },
                "aggregateRating": {
                    "@type": "AggregateRating",
                    "ratingValue": "5.0",
                    "ratingCount": "1"
                }
            },
            {
                "@type": "WebSite",
                "name": "Composive Documentation",
                "url": "https://gursimarsingh12.github.io/Composive/",
                "description": "Complete documentation for Composive - the responsive and adaptive UI library for Compose Multiplatform",
                "inLanguage": "en-US",
                "potentialAction": {
                    "@type": "SearchAction",
                    "target": "https://gursimarsingh12.github.io/Composive/search/?q={search_term_string}",
                    "query-input": "required name=search_term_string"
                }
            },
            {
                "@type": "TechArticle",
                "headline": "Composive - Responsive UI Library for Compose Multiplatform",
                "description": "Learn how to create responsive and adaptive UIs with Composive in your Compose Multiplatform projects",
                "author": {
                    "@type": "Person",
                    "name": "Gursimar Singh"
                },
                "datePublished": "2024-01-01",
                "dateModified": new Date().toISOString().split('T')[0],
                "publisher": {
                    "@type": "Organization",
                    "name": "Composive",
                    "url": "https://gursimarsingh12.github.io/Composive/"
                },
                "mainEntityOfPage": {
                    "@type": "WebPage",
                    "@id": "https://gursimarsingh12.github.io/Composive/"
                }
            },
            {
                "@type": "FAQPage",
                "mainEntity": [
                    {
                        "@type": "Question",
                        "name": "What is Composive?",
                        "acceptedAnswer": {
                            "@type": "Answer",
                            "text": "Composive is a responsive and adaptive UI library for Compose Multiplatform that helps developers create beautiful, responsive layouts across Android, iOS, Desktop, and Web platforms with automatic theme adaptation."
                        }
                    },
                    {
                        "@type": "Question",
                        "name": "How do I install Composive?",
                        "acceptedAnswer": {
                            "@type": "Answer",
                            "text": "Add JitPack repository to your settings.gradle.kts and include the dependency: implementation('com.github.Gursimarsingh12.composive:composive-responsive-adaptive:1.0.0')"
                        }
                    },
                    {
                        "@type": "Question",
                        "name": "Which platforms does Composive support?",
                        "acceptedAnswer": {
                            "@type": "Answer",
                            "text": "Composive supports Android (API 21+), iOS (iOS 13.0+), Desktop (Windows, macOS, Linux), and Web (Kotlin/Wasm, Kotlin/JS)."
                        }
                    },
                    {
                        "@type": "Question",
                        "name": "Is Composive free to use?",
                        "acceptedAnswer": {
                            "@type": "Answer",
                            "text": "Yes, Composive is completely free and open-source. You can use it in personal and commercial projects without any licensing fees."
                        }
                    }
                ]
            },
            {
                "@type": "HowTo",
                "name": "How to Create Responsive UI with Composive",
                "description": "Step-by-step guide to building responsive UIs with Composive in Compose Multiplatform",
                "totalTime": "PT30M",
                "estimatedCost": {
                    "@type": "MonetaryAmount",
                    "currency": "USD",
                    "value": "0"
                },
                "step": [
                    {
                        "@type": "HowToStep",
                        "name": "Install Composive",
                        "text": "Add JitPack repository and Composive dependency to your project"
                    },
                    {
                        "@type": "HowToStep",
                        "name": "Wrap with ComposiveTheme",
                        "text": "Wrap your app with ComposiveTheme to enable responsive behavior"
                    },
                    {
                        "@type": "HowToStep",
                        "name": "Use Device Configuration",
                        "text": "Use rememberDeviceConfiguration() to detect screen size and adapt layouts"
                    },
                    {
                        "@type": "HowToStep",
                        "name": "Apply Responsive Styling",
                        "text": "Use AppTheme.dimensions and AppTheme.typography for responsive styling"
                    }
                ]
            }
        ]
    };

    // Add structured data to page
    const script = document.createElement('script');
    script.type = 'application/ld+json';
    script.text = JSON.stringify(structuredData);
    document.head.appendChild(script);
}

// Enhanced page tracking and SEO
function initializeSEO() {
    // Add structured data
    addStructuredData();

    // Add comprehensive meta tags
    const metaTags = [
        { name: 'robots', content: 'index, follow, max-snippet:-1, max-image-preview:large, max-video-preview:-1' },
        { name: 'googlebot', content: 'index, follow, max-snippet:-1, max-image-preview:large, max-video-preview:-1' },
        { name: 'bingbot', content: 'index, follow' },
        { name: 'slurp', content: 'index, follow' }, // Yahoo
        { name: 'duckduckbot', content: 'index, follow' }, // DuckDuckGo
        { property: 'og:site_name', content: 'Composive Documentation' },
        { property: 'og:locale', content: 'en_US' },
        { property: 'og:type', content: 'website' },
        { name: 'twitter:site', content: '@composive' },
        { name: 'twitter:creator', content: '@gursimar_singh' },
        { name: 'twitter:card', content: 'summary_large_image' },
        { name: 'theme-color', content: '#673ab7' },
        { name: 'msapplication-TileColor', content: '#673ab7' },
        { name: 'apple-mobile-web-app-capable', content: 'yes' },
        { name: 'apple-mobile-web-app-status-bar-style', content: 'default' },
        { name: 'apple-mobile-web-app-title', content: 'Composive' },
        { name: 'application-name', content: 'Composive' },
        { name: 'mobile-web-app-capable', content: 'yes' },
        { name: 'language', content: 'en-US' },
        { name: 'distribution', content: 'global' },
        { name: 'rating', content: 'general' },
        { name: 'revisit-after', content: '7 days' },
        { name: 'referrer', content: 'origin-when-cross-origin' },
        { name: 'format-detection', content: 'telephone=no' },
        { name: 'generator', content: 'MkDocs' },
        { name: 'coverage', content: 'Worldwide' },
        { name: 'target', content: 'all' },
        { name: 'HandheldFriendly', content: 'True' },
        { name: 'MobileOptimized', content: '320' },
        { name: 'apple-touch-fullscreen', content: 'yes' },
        // Additional keyword meta tags
        { name: 'subject', content: 'Compose Multiplatform UI Library' },
        { name: 'topic', content: 'Mobile Development, Cross Platform, UI Framework' },
        { name: 'summary', content: 'Responsive and adaptive UI library for Compose Multiplatform applications' },
        { name: 'category', content: 'Technology, Software Development, Mobile Apps' },
        { name: 'classification', content: 'Business' },
        { name: 'designer', content: 'Gursimar Singh' },
        { name: 'owner', content: 'Gursimar Singh' },
        { name: 'reply-to', content: 'anonymouslike083@gmail.com' },
        { name: 'url', content: 'https://gursimarsingh12.github.io/Composive/' },
        { name: 'identifier-URL', content: 'https://gursimarsingh12.github.io/Composive/' }
    ];

    metaTags.forEach(tag => {
        if (!document.querySelector(`meta[name="${tag.name}"], meta[property="${tag.property}"]`)) {
            const metaElement = document.createElement('meta');
            if (tag.name) metaElement.name = tag.name;
            if (tag.property) metaElement.property = tag.property;
            metaElement.content = tag.content;
            document.head.appendChild(metaElement);
        }
    });

    // Add canonical URL if not present
    if (!document.querySelector('link[rel="canonical"]')) {
        const canonical = document.createElement('link');
        canonical.rel = 'canonical';
        canonical.href = window.location.href;
        document.head.appendChild(canonical);
    }

    // Add DNS prefetch for better performance
    const dnsPrefetchDomains = [
        'fonts.googleapis.com',
        'fonts.gstatic.com',
        'www.google-analytics.com',
        'github.com',
        'jitpack.io'
    ];

    dnsPrefetchDomains.forEach(domain => {
        const link = document.createElement('link');
        link.rel = 'dns-prefetch';
        link.href = `//${domain}`;
        document.head.appendChild(link);
    });

    // Preload important resources
    const preloadResources = [
        { href: '/assets/stylesheets/main.css', as: 'style' },
        { href: '/assets/javascripts/main.js', as: 'script' }
    ];

    preloadResources.forEach(resource => {
        const link = document.createElement('link');
        link.rel = 'preload';
        link.href = resource.href;
        link.as = resource.as;
        document.head.appendChild(link);
    });

    // Add alternate language declarations (if needed in future)
    const alternateLanguages = [
        { lang: 'en', href: 'https://gursimarsingh12.github.io/Composive/' }
    ];

    alternateLanguages.forEach(alt => {
        const link = document.createElement('link');
        link.rel = 'alternate';
        link.hreflang = alt.lang;
        link.href = alt.href;
        document.head.appendChild(link);
    });
}

// Enhanced performance monitoring for SEO
function monitorPerformance() {
    if ('performance' in window) {
        window.addEventListener('load', () => {
            setTimeout(() => {
                const perfData = performance.getEntriesByType('navigation')[0];
                if (perfData) {
                    // Track page load time for SEO optimization
                    const loadTime = perfData.loadEventEnd - perfData.fetchStart;
                    const domContentLoaded = perfData.domContentLoadedEventEnd - perfData.fetchStart;
                    const firstPaint = performance.getEntriesByType('paint')[0]?.startTime || 0;

                    console.log(`Page load time: ${loadTime}ms`);
                    console.log(`DOM Content Loaded: ${domContentLoaded}ms`);
                    console.log(`First Paint: ${firstPaint}ms`);

                    // Track Core Web Vitals if available
                    if (typeof webVitals !== 'undefined') {
                        webVitals.getCLS(console.log);
                        webVitals.getFID(console.log);
                        webVitals.getFCP(console.log);
                        webVitals.getLCP(console.log);
                        webVitals.getTTFB(console.log);
                    }
                }
            }, 0);
        });
    }
}

// Track user engagement for SEO signals
function trackEngagement() {
    let startTime = Date.now();
    let maxScroll = 0;

    // Track scroll depth
    window.addEventListener('scroll', () => {
        const scrollTop = window.pageYOffset || document.documentElement.scrollTop;
        const docHeight = document.documentElement.scrollHeight - window.innerHeight;
        const scrollPercent = (scrollTop / docHeight) * 100;
        maxScroll = Math.max(maxScroll, scrollPercent);
    });

    // Track time on page
    window.addEventListener('beforeunload', () => {
        const timeOnPage = Date.now() - startTime;
        console.log(`Time on page: ${timeOnPage}ms, Max scroll: ${maxScroll}%`);
    });

    // Track click events
    document.addEventListener('click', (e) => {
        if (e.target.tagName === 'A') {
            console.log(`Link clicked: ${e.target.href}`);
        }
    });
}

// Initialize SEO enhancements when DOM is ready
if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', () => {
        initializeSEO();
        monitorPerformance();
        trackEngagement();
    });
} else {
    initializeSEO();
    monitorPerformance();
    trackEngagement();
} 
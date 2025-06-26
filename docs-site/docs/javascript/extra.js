document.addEventListener('DOMContentLoaded', function() {
    addSmoothScrolling();
    addPlatformDemo();
});

function addSmoothScrolling() {
    const links = document.querySelectorAll('a[href^="#"]');
    links.forEach(function(link) {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            const target = document.querySelector(this.getAttribute('href'));
            if (target) {
                target.scrollIntoView({
                    behavior: 'smooth',
                    block: 'start'
                });
            }
        });
    });
}

function addPlatformDemo() {
    const platformDemo = document.getElementById('platform-demo');
    if (platformDemo) {
        const userAgent = navigator.userAgent;
        let platform = 'Unknown';
        
        if (userAgent.includes('Android')) {
            platform = 'Android 🤖';
        } else if (userAgent.includes('iPhone') || userAgent.includes('iPad')) {
            platform = 'iOS 🍎';
        } else if (userAgent.includes('Mac')) {
            platform = 'macOS 💻';
        } else if (userAgent.includes('Windows')) {
            platform = 'Windows 🪟';
        } else if (userAgent.includes('Linux')) {
            platform = 'Linux 🐧';
        }
        
        platformDemo.innerHTML = `
            <div class="platform-detection">
                <h4>🔍 Live Platform Detection</h4>
                <p>Your current platform: <strong>${platform}</strong></p>
                <p><em>Composive would automatically adapt your app's theme for this platform!</em></p>
            </div>
        `;
    }
}

window.addEventListener('scroll', function() {
    const scrollTop = document.getElementById('scroll-top');
    if (scrollTop) {
        if (window.pageYOffset > 300) {
            scrollTop.style.display = 'block';
        } else {
            scrollTop.style.display = 'none';
        }
    }
});

document.addEventListener('click', function(e) {
    if (e.target.matches('[data-md-color-scheme]')) {
        document.body.style.transition = 'all 0.3s ease';
        setTimeout(function() {
            document.body.style.transition = '';
        }, 300);
    }
}); 
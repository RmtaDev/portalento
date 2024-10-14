document.addEventListener('DOMContentLoaded', function() {
	let isMenuOpen = false;
	let isTextReaderActive = false;
	let selectedElement = null;
	let isZoomed = false;
	let isMonocromaticoActive = false;

	const toggleTextReader = () => {
		isTextReaderActive = !isTextReaderActive;
		clearStyles();
		const speakerIcon = document.getElementById('speakerIcon');
		speakerIcon.className = isTextReaderActive ? 'fas fa-volume-up' : 'fas fa-volume-mute';
	};

	const detectElements = (event) => {
		if (isTextReaderActive) {
			event.preventDefault();
			selectedElement = event.target;
			selectedElement.style.cssText = 'outline: 2px dashed red; animation: pulseOutline 1s infinite;';
			const text = selectedElement.innerText || selectedElement.textContent;
			window.speechSynthesis.cancel();
			window.speechSynthesis.speak(new SpeechSynthesisUtterance(text));
		}
	};

	const clearStyles = () => {
		if (selectedElement) {
			selectedElement.style.cssText = '';
		}
		selectedElement = null;
	};

	const toggleZoom = () => {
		const startZoom = isZoomed ? 1.15 : 1;
		const endZoom = isZoomed ? 1 : 1.15;
		const duration = 500;
		const stepTime = 20;

		let currentZoom = startZoom;
		const stepZoom = (endZoom - startZoom) / (duration / stepTime);

		const zoomAnimation = setInterval(() => {
			currentZoom += stepZoom;
			document.body.style.zoom = currentZoom;

			if ((stepZoom > 0 && currentZoom >= endZoom) || (stepZoom < 0 && currentZoom <= endZoom)) {
				clearInterval(zoomAnimation);
			}
		}, stepTime);

		isZoomed = !isZoomed;
		const zoomIcon = document.getElementById('zoomIcon');
		zoomIcon.class = isZoomed ? 'fa fa-search-minus' : 'fa fa-search-plus';
	};

	const toggleMonocromatico = () => {
		isMonocromaticoActive = !isMonocromaticoActive;
		const monocromaticoIcon = document.getElementById('monocromaticoIcon');

		if (isMonocromaticoActive) {
			document.documentElement.classList.add('monocromatico-animate-to');
			monocromaticoIcon.class = 'fas fa-eye monocromatico';
		} else {
			document.documentElement.classList.remove('monocromatico-animate-to');
			monocromaticoIcon.class = 'fas fa-eye-slash';
		}
	};

	document.documentElement.addEventListener('animationend', (event) => {
		if (event.animationName === 'fadeToGray') {
			document.documentElement.classList.add('monocromatico');
		}
		if (event.animationName === 'fadeFromGray') {
			document.documentElement.classList.remove('monocromatico');
		}
		document.documentElement.classList.remove('monocromatico-animate-to', 'monocromatico-animate-from');
	});

	document.getElementById('openModalBtn').addEventListener('click', () => {
		isMenuOpen = !isMenuOpen;
		document.getElementById('radialMenu').classList.toggle('show', isMenuOpen);
	});

	document.getElementById('speakerBtn').addEventListener('click', toggleTextReader);
	document.addEventListener('mousedown', detectElements);
	document.addEventListener('mouseup', clearStyles);

	document.getElementById('zoomBtn').addEventListener('click', toggleZoom);
	document.getElementById('monocromaticoBtn').addEventListener('click', toggleMonocromatico);
});

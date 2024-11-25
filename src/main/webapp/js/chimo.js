document.addEventListener("keydown", (event) => {
    // Detectar combinación específica (por ejemplo: Ctrl + Shift + L)
    if (event.ctrlKey && event.shiftKey && event.key === "L") {
        const audio = document.getElementById("audioElement");
        audio.play(); // Reproducir el audio
        console.log("Combinación de teclas detectada: Ctrl + Shift + L");
    }
});
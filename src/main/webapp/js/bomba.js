document.addEventListener("keydown", (event) => {
    // Detectar combinación específica (por ejemplo: Ctrl + Shift + Ñ)
    if (event.ctrlKey && event.shiftKey && event.key === "Ñ") {
        const audio = document.getElementById("audioElemento");
        audio.play(); // Reproducir el audio
        console.log("Combinación de teclas detectada: Ctrl + Shift + Ñ");
    }
});
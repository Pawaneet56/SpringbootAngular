from flask import Flask, request, jsonify
from latex_ocr import LatexOCR

app = Flask(__name__)
ocr = LatexOCR()  # Load LaTeX-OCR model

@app.route("/convert", methods=["POST"])
def convert():
    # Check if a file was uploaded
    if "file" not in request.files:
        return jsonify({"error": "No file provided"}), 400

    file = request.files["file"]

    # Check if the file is an image or PDF
    if file.filename.endswith(".pdf"):
        latex_code = ocr.pdf_to_latex(file.stream)
    else:
        latex_code = ocr.image_to_latex(file.stream)

    return jsonify({"latex": latex_code})

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)

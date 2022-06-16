import pyotp

from flask import Flask, request, abort
from flask_cors import CORS


app = Flask(__name__)
CORS(app)

users = {}


@app.route("/")
def hello_world():
    return "<p>Hello, World!</p>"


@app.route('/register', methods=['POST'])
def register():
    content = request.json
    secret = pyotp.random_base32()
    users[content["username"]] = {
        "password": content["password"],
        "secret": secret,
        "confirmed": False
    }
    url = pyotp.totp.TOTP(secret).provisioning_uri(
        name=content["username"], issuer_name='SIN App')

    return {"qr_url": url}


@app.route('/login', methods=['POST'])
def login():
    content = request.json
    username = content["username"]
    password = content["password"]
    token = content["token"]
    print(users)
    secret = users[username]["secret"]
    totp = pyotp.TOTP(secret)
    print(content)
    if totp.verify(token) and password == users[username]["password"]:
        return "ok"
    else:
        abort(401)


if __name__ == "__main__":
    app.run(debug=True)

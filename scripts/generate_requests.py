import argparse
import base64
import json
import random
import requests
from datetime import datetime
import sys


def generate_name_list(url):
    resp = requests.get(url)
    return tuple(
        filter(lambda x: x != "", [line.rstrip("\r") for line in resp.text.split("\n")])
    )


LAST_NAME_LIST = generate_name_list(
    "https://raw.githubusercontent.com/smashew/NameDatabases/master/NamesDatabases/surnames/us.txt"
)
FIRST_NAME_LIST = generate_name_list(
    "https://raw.githubusercontent.com/smashew/NameDatabases/master/NamesDatabases/first%20names/us.txt"
)


def generate_request():
    try:
        result = {}
        result["method"] = "POST"
        result["url"] = "http://localhost:30080/api/users"
        # 1970-01-01 - 2020-12-31
        rand_date = datetime.fromtimestamp(random.randint(0, 1609426799))
        body_dic = {
            "firstName": random.choice(FIRST_NAME_LIST),
            "lastName": random.choice(LAST_NAME_LIST),
            "birthYear": rand_date.year,
            "birthMonth": rand_date.month,
            "birthDate": rand_date.day,
            "isDummy": args.dummy,
        }
        result["header"] = {"Content-Type": ["application/json"]}
        result["body"] = base64.b64encode(json.dumps(body_dic).encode()).decode()
        print(json.dumps(result))
    except BrokenPipeError:
        print("stop generating", file=sys.stderr)
        sys.exit(0)


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--dummy", help="with dummy request", action="store_true")
    args = parser.parse_args()
    while True:
        generate_request()

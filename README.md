# spring-mvc-warmup

Spring + Kubernetesでウォームアップ→完了後サービスイン、を行うサンプル

## Application

ユーザ登録を行うだけのWebAPIを用意しています。実際にはDB書き込みなどは行わず、標準出力ログとして流れるだけです。

```bash
$ cat /tmp/req.json
{
    "firstName": "Taro",
    "lastName": "Yamada",
    "birthYear": 1990,
    "birthMonth": 5,
    "birthDate": 3
}
$ curl -s -X POST -H "Content-Type: application/json" http://localhost:30080/api/users -d @/tmp/req.json | jq .
{
  "isSucceeded": true,
  "userAddResponse": {
    "user": {
      "userId": {
        "id": "59036d2f-55e5-4977-bbe7-caaa515ba030"
      },
      "name": {
        "firstName": "Taro",
        "lastName": "Yamada"
      },
      "birthday": {
        "date": "1990-05-03"
      },
      "isDummy": false
    }
  }
}
```

## Deploy

Kubernetes v1.19を想定しています。Widnows10 + WSL2で、Docker Desktop付属のKubernetes環境で確認しております。

```bash
$ kubectl apply -f ./k8s
```

## Benchmark

[scripts/generate_requests.py](scripts/generate_requests.py)でリクエストを生成、 [vegeta](https://github.com/tsenart/vegeta)
でベンチマークを実施できるようにしております。 pythonスクリプトは [requirements.txt](scripts/requirements.txt)
の依存が必要、また [NameDatabases](https://github.com/smashew/NameDatabases) をもとにリクエストを作ってます。

```bash
$ python3 scripts/generate_requests.py | vegeta attack -rate=1000/s -lazy -format=json -duration=60s > /tmp/result.bin
```
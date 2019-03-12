一 动态路由实现
{ "filters": [], "id": "jd_route", "order": 0, "predicates": [{ "args": { "pattern": "/jd" }, "name": "Path" }], "uri": "http://www.jd.com" }
完整参数
{
    "route_id": "CompositeDiscoveryClient_SPRING-CLOUD-SERVICE",
    "route_definition": {
      "id": "CompositeDiscoveryClient_SPRING-CLOUD-SERVICE",
      "predicates": [
        {
          "name": "Path",
          "args": {
            "pattern": "/spring-cloud-service/**"
          }
        }
      ],
      "filters": [
        {
          "name": "RewritePath",
          "args": {
            "regexp": "/spring-cloud-service/(?<remaining>.*)",
            "replacement": "/${remaining}"
          }
        }
      ],
      "uri": "lb://SPRING-CLOUD-SERVICE",
      "order": 0
    },
    "order": 0
  }
# Test script for Weather API
$baseUrl = "http://localhost:80/api/warmest-db"
$headers = @{"X-API-Version"="1"}

Write-Host "`n=== Weather API Test Suite ===" -ForegroundColor Cyan

# 1. GetWarmest() returns null
Write-Host "`n1. GetWarmest() - should return null/404" -ForegroundColor Yellow
curl.exe -X GET "$baseUrl/warmest" -H "X-API-Version: 1"

# 2. put("a", 100) returns null
Write-Host "`n2. put('a', 100) - should return null (no previous value)" -ForegroundColor Yellow
curl.exe -X POST "$baseUrl/put?key=a&value=100" -H "X-API-Version: 1"

# 3. GetWarmest() returns a
Write-Host "`n3. GetWarmest() - should return 'a'" -ForegroundColor Yellow
curl.exe -X GET "$baseUrl/warmest" -H "X-API-Version: 1"

# 4. put("a", 101) returns 100
Write-Host "`n4. put('a', 101) - should return 100 (previous value)" -ForegroundColor Yellow
curl.exe -X POST "$baseUrl/put?key=a&value=101" -H "X-API-Version: 1"

# 5. put("a", 101) returns 101
Write-Host "`n5. put('a', 101) - should return 101 (previous value)" -ForegroundColor Yellow
curl.exe -X POST "$baseUrl/put?key=a&value=101" -H "X-API-Version: 1"

# 6. Get("a") returns 101
Write-Host "`n6. Get('a') - should return 101" -ForegroundColor Yellow
curl.exe -X GET "$baseUrl/get?key=a" -H "X-API-Version: 1"

# 7. GetWarmest() returns a
Write-Host "`n7. GetWarmest() - should return 'a'" -ForegroundColor Yellow
curl.exe -X GET "$baseUrl/warmest" -H "X-API-Version: 1"

# 8. Remove("a") return 101
Write-Host "`n8. Remove('a') - should return 101" -ForegroundColor Yellow
curl.exe -X DELETE "$baseUrl/remove?key=a" -H "X-API-Version: 1"

# 9. Remove("a") return null
Write-Host "`n9. Remove('a') - should return null/404" -ForegroundColor Yellow
curl.exe -X DELETE "$baseUrl/remove?key=a" -H "X-API-Version: 1"

# 10. GetWarmest() returns null
Write-Host "`n10. GetWarmest() - should return null/204" -ForegroundColor Yellow
curl.exe -X GET "$baseUrl/warmest" -H "X-API-Version: 1"

# 11. put("a", 100) returns null
Write-Host "`n11. put('a', 100) - should return null (no previous value)" -ForegroundColor Yellow
curl.exe -X POST "$baseUrl/put?key=a&value=100" -H "X-API-Version: 1"

# 12. put("b", 200) returns null
Write-Host "`n12. put('b', 200) - should return null (no previous value)" -ForegroundColor Yellow
curl.exe -X POST "$baseUrl/put?key=b&value=200" -H "X-API-Version: 1"

# 13. put("c", 300) returns null
Write-Host "`n13. put('c', 300) - should return null (no previous value)" -ForegroundColor Yellow
curl.exe -X POST "$baseUrl/put?key=c&value=300" -H "X-API-Version: 1"

# 14. GetWarmest() returns c
Write-Host "`n14. GetWarmest() - should return 'c'" -ForegroundColor Yellow
curl.exe -X GET "$baseUrl/warmest" -H "X-API-Version: 1"

# 15. Remove("b") return 200
Write-Host "`n15. Remove('b') - should return 200" -ForegroundColor Yellow
curl.exe -X DELETE "$baseUrl/remove?key=b" -H "X-API-Version: 1"

# 16. GetWarmest() returns c
Write-Host "`n16. GetWarmest() - should return 'c'" -ForegroundColor Yellow
curl.exe -X GET "$baseUrl/warmest" -H "X-API-Version: 1"

# 17. Remove("c") return 300
Write-Host "`n17. Remove('c') - should return 300" -ForegroundColor Yellow
curl.exe -X DELETE "$baseUrl/remove?key=c" -H "X-API-Version: 1"

# 18. GetWarmest() returns a
Write-Host "`n18. GetWarmest() - should return 'a'" -ForegroundColor Yellow
curl.exe -X GET "$baseUrl/warmest" -H "X-API-Version: 1"

# 19. Remove("a") return 100
Write-Host "`n19. Remove('a') - should return 100" -ForegroundColor Yellow
curl.exe -X DELETE "$baseUrl/remove?key=a" -H "X-API-Version: 1"

# 20. GetWarmest() returns null
Write-Host "`n20. GetWarmest() - should return null/204" -ForegroundColor Yellow
curl.exe -X GET "$baseUrl/warmest" -H "X-API-Version: 1"

# 21. Remove("a") return null
Write-Host "`n21. Remove('a') - should return null/404" -ForegroundColor Yellow
curl.exe -X DELETE "$baseUrl/remove?key=a" -H "X-API-Version: 1"

Write-Host "`n=== Test Suite Complete ===" -ForegroundColor Cyan
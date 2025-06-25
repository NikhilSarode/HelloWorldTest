$services = @(
    @{ name = "webapp-service"; local = 3000; remote = 3000 },
    @{ name = "dept-webapp-service"; local = 3001; remote = 3001 }
)

foreach ($svc in $services) {
    $log = "$PSScriptRoot\pf-$($svc.name).log"
    $cmd = "kubectl port-forward svc/$($svc.name) $($svc.local):$($svc.remote)"

    Write-Host "Starting port-forward for $($svc.name)..."

    Start-Process powershell -ArgumentList @(
        "-NoProfile",
        "-Command",
        "& { $cmd | Out-File -FilePath '$log' -Append }"
    ) -WindowStyle Hidden

    Write-Host "Started forwarding: http://localhost:$($svc.local) → $($svc.name):$($svc.remote)"
    Write-Host "Log file: $log`n"
}

Write-Host "Use Task Manager or 'Get-Process kubectl' to view active forwards."

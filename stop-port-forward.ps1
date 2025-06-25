$kubectlProcesses = Get-Process kubectl -ErrorAction SilentlyContinue

if ($null -eq $kubectlProcesses) {
    Write-Host "No running kubectl port-forward processes found."
} else {
    Write-Host "Stopping kubectl port-forward processes..."
    $kubectlProcesses | Stop-Process -Force
    Write-Host "All port-forwards stopped."
}

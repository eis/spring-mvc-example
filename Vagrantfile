# -*- mode: ruby -*-
# vi: set ft=ruby :

$fixlocale = <<SCRIPT
echo "LC_CTYPE=\"en_US.UTF-8\"" | sudo tee -a /etc/default/locale > /dev/null
SCRIPT

# Vagrantfile API/syntax version. Don't touch unless you know what you're doing!
VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
  config.vm.box = "ubuntu/xenial64"
  config.vm.box_version = "20161022.0.0"
  config.vm.provider "libvirt" do |v, override|
    override.vm.box = "mkutsevol/xenial"
    override.vm.box_version = "0.1"
  end

  config.vm.box_check_update = false

  config.vm.network :forwarded_port, guest: 8080, host: 8080

  config.vm.provider "virtualbox" do |v|
    v.memory = 2048
    v.cpus = 2
  end

  config.vm.provision :shell, :inline => $fixlocale, :privileged => true
  config.vm.provision :shell, :path => "ubuntu-deps.sh", :privileged => true
  config.vm.provision :shell, :path => "add-tomcat-admin.sh", :privileged => true
  config.vm.provision :shell, :inline => 'systemctl restart tomcat8', :privileged => true
end

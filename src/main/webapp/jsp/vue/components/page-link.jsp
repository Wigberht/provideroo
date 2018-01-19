<script type="text/x-template" id="link-template">
    <li v-if="command===''"
        v-bind:class="{active: active=='true'}">
        <a :href="link">
            <i v-if="icon!==''" class="material-icons left">{{icon}}</i>
            {{message}}
        </a>
    </li>
    <li v-else>
        <a v-on:click.stop="runCommand">
            <i v-if="icon!=''" class="material-icons left">{{icon}}</i>
            {{message}}
        </a>
    </li>

</script>

<script>
    Vue.component('page-link', {
        props: {
            message: {
                "default": "",
                type: String
            },
            active: {
                "default": "false",
                type: String
            },
            page: {
                "default": "",
                type: String
            },
            link: {
                "default": "#",
                type: String
            },
            title: {
                "default": "",
                type: String
            },
            icon: {
                "default": "",
                type: String
            },
            command: {

                "default": "",
                type: String
            },
            method: {
                "default": "GET",
                type: String
            }

        },
        template: "#link-template",
        methods: {
            runCommand: function () {
                runCommand(this.command, this.method);
            }
        },
        mounted: function () {

        }
    })
</script>
